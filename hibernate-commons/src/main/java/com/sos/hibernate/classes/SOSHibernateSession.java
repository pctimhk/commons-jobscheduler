package com.sos.hibernate.classes;

import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.NonUniqueResultException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;
import org.hibernate.internal.StatelessSessionImpl;
import org.hibernate.jdbc.Work;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sos.hibernate.exceptions.SOSHibernateConfigurationException;
import com.sos.hibernate.exceptions.SOSHibernateConnectionException;
import com.sos.hibernate.exceptions.SOSHibernateCriteriaException;
import com.sos.hibernate.exceptions.SOSHibernateException;
import com.sos.hibernate.exceptions.SOSHibernateInvalidSessionException;
import com.sos.hibernate.exceptions.SOSHibernateOpenSessionException;
import com.sos.hibernate.exceptions.SOSHibernateQueryException;
import com.sos.hibernate.exceptions.SOSHibernateQueryNonUniqueResultException;
import com.sos.hibernate.exceptions.SOSHibernateSessionException;
import com.sos.hibernate.exceptions.SOSHibernateTransactionException;

import sos.util.SOSDate;

public class SOSHibernateSession implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(SOSHibernateSession.class);
    private final SOSHibernateFactory factory;
    private Object currentSession;
    private boolean isStatelessSession = false;
    private boolean isGetCurrentSession = false;
    private FlushMode defaultHibernateFlushMode = FlushMode.ALWAYS;
    private String identifier;
    private SOSHibernateSQLExecutor sqlExecutor;
    public static final int LIMIT_IN_CLAUSE = 1000;

    /** use factory.openSession() or factory.openStatelessSession(); */
    protected SOSHibernateSession(SOSHibernateFactory hibernateFactory) {
        this.factory = hibernateFactory;
    }

    public void setIdentifier(String val) {
        identifier = val;
    }

    protected void setIsStatelessSession(boolean val) {
        isStatelessSession = val;
    }

    protected void setIsGetCurrentSession(boolean val) {
        isGetCurrentSession = val;
    }

    public void setHibernateFlushMode(FlushMode flushMode) {
        if (currentSession instanceof Session) {
            Session session = (Session) currentSession;
            session.setHibernateFlushMode(flushMode);
        }
    }

    public void setCacheMode(CacheMode cacheMode) {
        if (currentSession instanceof Session) {
            Session session = (Session) currentSession;
            session.setCacheMode(cacheMode);
        }
    }

    public boolean isStatelessSession() {
        return isStatelessSession;
    }

    public boolean isGetCurrentSession() {
        return isGetCurrentSession;
    }

    public boolean isOpen() {
        if (currentSession != null) {
            if (isStatelessSession) {
                StatelessSession session = ((StatelessSession) currentSession);
                return session.isOpen();
            } else {
                Session session = (Session) currentSession;
                return session.isOpen();
            }
        }
        return false;
    }

    public boolean isConnected() {
        if (currentSession != null) {
            if (isStatelessSession) {
                StatelessSession session = ((StatelessSession) currentSession);
                return session.isConnected();
            } else {
                Session session = (Session) currentSession;
                return session.isConnected();
            }
        }
        return false;
    }

    public SOSHibernateFactory getFactory() {
        return factory;
    }

    public String getIdentifier() {
        return identifier;
    }

    public FlushMode getHibernateFlushMode() {
        if (!isStatelessSession && currentSession != null) {
            Session session = (Session) currentSession;
            return session.getHibernateFlushMode();
        }
        return null;
    }

    public CacheMode getCacheMode() {
        if (!isStatelessSession && currentSession != null) {
            Session session = (Session) currentSession;
            return session.getCacheMode();
        }
        return null;
    }

    public Object getCurrentSession() {
        return currentSession;
    }

    public Connection getConnection() throws SOSHibernateInvalidSessionException, SOSHibernateConnectionException {
        String method = getMethodName("getConnection");
        LOGGER.debug(String.format("%s", method));
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("session is NULL");
        }
        try {
            if (isStatelessSession) {
                StatelessSessionImpl sf = (StatelessSessionImpl) currentSession;
                return sf.connection();
            } else {
                SessionImpl sf = (SessionImpl) currentSession;
                return sf.connection();
            }
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e);
            } else {
                throw new SOSHibernateConnectionException(e);
            }
        } catch (PersistenceException e) {
            throw new SOSHibernateConnectionException(e);
        }
    }

    public String getLastSequenceValue(String sequenceName) throws SOSHibernateInvalidSessionException, SOSHibernateQueryNonUniqueResultException,
            SOSHibernateQueryException {
        String stmt = factory.getSequenceLastValString(sequenceName);
        return stmt == null ? null : getSingleValueNativeQuery(stmt);
    }

    public SOSHibernateSQLExecutor getSQLExecutor() {
        if (sqlExecutor == null) {
            sqlExecutor = new SOSHibernateSQLExecutor(this);
        }
        return sqlExecutor;
    }

    protected void openSession() throws SOSHibernateOpenSessionException {
        String method = getMethodName("openSession");
        if (currentSession != null) {
            LOGGER.debug(String.format("%s: close currentSession", method));
            closeSession();
        }
        LOGGER.debug(String.format("%s: isStatelessSession=%s, isGetCurrentSession=%s", method, isStatelessSession, isGetCurrentSession));
        try {
            if (isStatelessSession) {
                currentSession = factory.getSessionFactory().openStatelessSession();
            } else {
                Session session = null;
                if (isGetCurrentSession) {
                    session = factory.getSessionFactory().getCurrentSession();
                } else {
                    session = factory.getSessionFactory().openSession();
                }
                if (defaultHibernateFlushMode != null) {
                    session.setHibernateFlushMode(defaultHibernateFlushMode);
                }
                currentSession = session;
            }
        } catch (IllegalStateException e) {
            throw new SOSHibernateOpenSessionException(e);
        } catch (PersistenceException e) {
            throw new SOSHibernateOpenSessionException(e);
        }
    }

    /** @deprecated
     * 
     *             use factory.openSession() or factory.openStatelessSession(); */
    @Deprecated
    public void connect() throws SOSHibernateConfigurationException, SOSHibernateOpenSessionException {
        String method = getMethodName("connect");
        openSession();
        String connFile = (factory.getConfigFile().isPresent()) ? factory.getConfigFile().get().toAbsolutePath().toString() : "without config file";
        int isolationLevel = getFactory().getTransactionIsolation();
        LOGGER.debug(String.format("%s: autocommit=%s, transaction isolation=%s, %s", method, getFactory().getAutoCommit(), SOSHibernateFactory
                .getTransactionIsolationName(isolationLevel), connFile));

    }

    public int executeUpdateNativeQuery(String sql) throws SOSHibernateInvalidSessionException, SOSHibernateQueryException {
        return executeUpdate(createNativeQuery(sql));
    }

    public int executeUpdate(String hql) throws SOSHibernateInvalidSessionException, SOSHibernateQueryException {
        return executeUpdate(createQuery(hql));
    }

    /** execute NativeQuery or Query */
    @SuppressWarnings("deprecation")
    public int executeUpdate(Query<?> query) throws SOSHibernateInvalidSessionException, SOSHibernateQueryException {
        String method = getMethodName("executeUpdate");
        LOGGER.debug(String.format("%s: query[%s]", method, query.getQueryString()));
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("session is NULL");
        }
        try {
            return query.executeUpdate();
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e, query);
            } else {
                throw new SOSHibernateQueryException(e, query);
            }
        } catch (PersistenceException e) {
            throw new SOSHibernateQueryException(e, query);
        }
    }

    public void clearSession() throws SOSHibernateInvalidSessionException, SOSHibernateSessionException {
        String method = getMethodName("clearSession");
        LOGGER.debug(String.format("%s", method));
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("session is NULL");
        }
        try {
            if (!isStatelessSession) {
                Session session = (Session) currentSession;
                session.clear();
            }
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e);
            } else {
                throw new SOSHibernateSessionException(e);
            }
        } catch (PersistenceException e) {
            throw new SOSHibernateSessionException(e);
        }
    }

    public void sessionDoWork(Work work) throws SOSHibernateInvalidSessionException, SOSHibernateSessionException {
        String method = getMethodName("sessionDoWork");
        LOGGER.debug(String.format("%s", method));
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("session is NULL");
        }
        try {
            if (!isStatelessSession) {
                Session session = (Session) currentSession;
                session.doWork(work);
            }
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e);
            } else {
                throw new SOSHibernateSessionException(e);
            }
        } catch (PersistenceException e) {
            throw new SOSHibernateSessionException(e);
        }
    }

    public void reopen() throws SOSHibernateOpenSessionException {
        String method = getMethodName("reopen");
        LOGGER.debug(String.format("%s: isStatelessSession=%s", method, isStatelessSession));
        closeSession();
        openSession();
    }

    /** @deprecated
     * 
     *             use close(); */
    @Deprecated
    public void disconnect() {
        close();
    }

    public void close() {
        String method = getMethodName("close");
        LOGGER.debug(String.format("%s", method));
        closeTransaction();
        closeSession();
    }

    public void beginTransaction() throws SOSHibernateInvalidSessionException, SOSHibernateTransactionException {
        String method = getMethodName("beginTransaction");
        try {
            if (getFactory().getAutoCommit()) {
                LOGGER.debug(String.format("%s: skip (autoCommit is true)", method));
                return;
            }
        } catch (SOSHibernateConfigurationException e) {
            throw new SOSHibernateTransactionException("can't get configured autocommit", e);
        }
        LOGGER.debug(String.format("%s", method));
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("session is NULL");
        }
        try {
            if (isStatelessSession) {
                StatelessSession session = ((StatelessSession) currentSession);
                session.beginTransaction();
            } else {
                Session session = ((Session) currentSession);
                session.beginTransaction();
            }
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e);
            } else {
                throw new SOSHibernateTransactionException(e);
            }
        } catch (PersistenceException e) {
            throw new SOSHibernateTransactionException(e);
        }
    }

    public void commit() throws SOSHibernateInvalidSessionException, SOSHibernateTransactionException {
        String method = getMethodName("commit");
        try {
            if (getFactory().getAutoCommit()) {
                LOGGER.debug(String.format("%s: skip (autoCommit is true)", method));
                return;
            }
        } catch (SOSHibernateConfigurationException e) {
            throw new SOSHibernateTransactionException("can't get configured autocommit", e);
        }
        LOGGER.debug(String.format("%s", method));
        Transaction tr = getTransaction();
        if (tr == null) {
            throw new SOSHibernateTransactionException("transaction is NULL");
        }
        try {
            if (!isStatelessSession) {
                ((Session) currentSession).flush();
            }
            tr.commit();
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e);
            } else {
                throw new SOSHibernateTransactionException(e);
            }
        } catch (PersistenceException e) {
            throw new SOSHibernateTransactionException(e);
        }
    }

    public void rollback() throws SOSHibernateInvalidSessionException, SOSHibernateTransactionException {
        String method = getMethodName("rollback");
        try {
            if (getFactory().getAutoCommit()) {
                LOGGER.debug(String.format("%s: skip (autoCommit is true)", method));
                return;
            }
        } catch (SOSHibernateConfigurationException e) {
            throw new SOSHibernateTransactionException("can't get configured autocommit", e);
        }
        LOGGER.debug(String.format("%s", method));
        Transaction tr = getTransaction();
        if (tr == null) {
            throw new SOSHibernateTransactionException("transaction is NULL");
        }
        try {
            tr.rollback();
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e);
            } else {
                throw new SOSHibernateTransactionException(e);
            }
        } catch (PersistenceException e) {
            throw new SOSHibernateTransactionException(e);
        }
    }

    public Transaction getTransaction() throws SOSHibernateInvalidSessionException, SOSHibernateTransactionException {
        Transaction tr = null;
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("session is NULL");
        }
        try {
            if (isStatelessSession) {
                StatelessSession s = ((StatelessSession) currentSession);
                tr = s.getTransaction();
            } else {
                Session s = ((Session) currentSession);
                tr = s.getTransaction();
            }
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e);
            } else {
                throw new SOSHibernateTransactionException(e);
            }
        } catch (PersistenceException e) {
            throw new SOSHibernateTransactionException(e);
        }
        return tr;
    }

    public void save(Object item) throws SOSHibernateInvalidSessionException, SOSHibernateSessionException {
        String method = getMethodName("save");
        LOGGER.debug(String.format("%s: item=%s", method, item));
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("session is NULL");
        }
        try {
            if (isStatelessSession) {
                StatelessSession session = ((StatelessSession) currentSession);
                session.insert(item);
            } else {
                Session session = ((Session) currentSession);
                session.save(item);
                session.flush();
            }
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e);
            } else {
                throw new SOSHibernateSessionException(e);
            }
        } catch (PersistenceException e) {
            throw new SOSHibernateSessionException(e);
        }
    }

    public void update(Object item) throws SOSHibernateInvalidSessionException, SOSHibernateSessionException {
        String method = getMethodName("update");
        LOGGER.debug(String.format("%s: item=%s", method, item));
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("session is NULL");
        }
        try {
            if (isStatelessSession) {
                StatelessSession session = ((StatelessSession) currentSession);
                session.update(item);
            } else {
                Session session = ((Session) currentSession);
                session.update(item);
                session.flush();
            }
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e);
            } else {
                throw new SOSHibernateSessionException(e);
            }
        } catch (PersistenceException e) {
            throw new SOSHibernateSessionException(e);
        }
    }

    public Object saveOrUpdate(Object item) throws SOSHibernateInvalidSessionException, SOSHibernateSessionException {
        String method = getMethodName("saveOrUpdate");
        LOGGER.debug(String.format("%s: item=%s", method, item));
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("session is NULL");
        }
        try {
            if (isStatelessSession) {
                StatelessSession session = ((StatelessSession) currentSession);
                Object id = null;
                try {
                    id = SOSHibernate.getId(item);
                    if (id == null) {
                        throw new SOSHibernateException(String.format("not found @Id annotated public getter method [%s]", item.getClass()
                                .getName()));
                    }
                } catch (SOSHibernateException e) {
                    throw new SOSHibernateSessionException(e.getMessage(), e.getCause());
                }
                Object dbItem = get(item.getClass(), (Serializable) id);
                if (dbItem == null) {
                    session.insert(item);
                } else {
                    session.update(item);
                }
            } else {
                Session session = ((Session) currentSession);
                session.saveOrUpdate(item);
                session.flush();
            }
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e);
            } else {
                throw new SOSHibernateSessionException(e);
            }
        } catch (PersistenceException e) {
            throw new SOSHibernateSessionException(e);
        }
        return item;
    }

    public void delete(Object item) throws SOSHibernateInvalidSessionException, SOSHibernateSessionException {
        String method = getMethodName("delete");
        LOGGER.debug(String.format("%s: item=%s", method, item));
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("session is NULL");
        }
        try {
            if (isStatelessSession) {
                StatelessSession session = ((StatelessSession) currentSession);
                session.delete(item);
            } else {
                Session session = ((Session) currentSession);
                session.delete(item);
                session.flush();
            }
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e);
            } else {
                throw new SOSHibernateSessionException(e);
            }
        } catch (PersistenceException e) {
            throw new SOSHibernateSessionException(e);
        }
    }

    public void refresh(Object object) throws SOSHibernateInvalidSessionException, SOSHibernateSessionException {
        refresh(null, object);
    }

    public void refresh(String entityName, Object object) throws SOSHibernateInvalidSessionException, SOSHibernateSessionException {
        String method = getMethodName("refresh");
        LOGGER.debug(String.format("%s: entityName=%s", method, entityName));
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("session is NULL");
        }
        try {
            if (isStatelessSession) {
                StatelessSession session = ((StatelessSession) currentSession);
                if (entityName == null) {
                    session.refresh(object);
                } else {
                    session.refresh(entityName, object);
                }
            } else {
                Session session = (Session) currentSession;
                if (entityName == null) {
                    session.refresh(object);
                } else {
                    session.refresh(entityName, object);
                }
            }
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e);
            } else {
                throw new SOSHibernateSessionException(e);
            }
        } catch (PersistenceException e) {
            throw new SOSHibernateSessionException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> entityClass, Serializable id) throws SOSHibernateInvalidSessionException, SOSHibernateSessionException {
        String method = getMethodName("get");
        LOGGER.debug(String.format("%s: entityClass=%s", method, entityClass.getName()));
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("session is NULL");
        }
        try {
            if (isStatelessSession) {
                return (T) ((StatelessSession) currentSession).get(entityClass, id);
            } else {
                return ((Session) currentSession).get(entityClass, id);
            }
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e);
            } else {
                throw new SOSHibernateSessionException(e);
            }
        } catch (PersistenceException e) {
            throw new SOSHibernateSessionException(e);
        }
    }

    public <T> Query<T> createQuery(String hql) throws SOSHibernateInvalidSessionException, SOSHibernateQueryException {
        return createQuery(hql, null);
    }

    @SuppressWarnings("unchecked")
    public <T> Query<T> createQuery(String hql, Class<T> entityClass) throws SOSHibernateInvalidSessionException, SOSHibernateQueryException {
        String method = getMethodName("createQuery");
        LOGGER.debug(String.format("%s: hql=%s", method, hql));
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("session is NULL", hql);
        }
        Query<T> q = null;
        try {
            if (isStatelessSession) {
                if (entityClass == null) {
                    q = ((StatelessSession) currentSession).createQuery(hql);
                } else {
                    q = ((StatelessSession) currentSession).createQuery(hql, entityClass);
                }
            } else {
                if (entityClass == null) {
                    q = ((Session) currentSession).createQuery(hql);
                } else {
                    q = ((Session) currentSession).createQuery(hql, entityClass);
                }
            }
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e, hql);
            } else {
                throw new SOSHibernateQueryException(e, hql);
            }
        } catch (IllegalArgumentException e) {
            throw new SOSHibernateQueryException(e, hql);
        } catch (PersistenceException e) {
            throw new SOSHibernateQueryException(e, hql);
        }
        return q;
    }

    public <T> NativeQuery<T> createNativeQuery(String sql) throws SOSHibernateInvalidSessionException, SOSHibernateQueryException {
        return createNativeQuery(sql, null);
    }

    @SuppressWarnings("unchecked")
    public <T> NativeQuery<T> createNativeQuery(String sql, Class<T> entityClass) throws SOSHibernateInvalidSessionException,
            SOSHibernateQueryException {
        String method = getMethodName("createNativeQuery");
        LOGGER.debug(String.format("%s: sql=%s", method, sql));
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("session is NULL", sql);
        }
        NativeQuery<T> q = null;
        try {
            if (isStatelessSession) {
                if (entityClass == null) {
                    q = ((StatelessSession) currentSession).createNativeQuery(sql);
                } else {
                    q = ((StatelessSession) currentSession).createNativeQuery(sql, entityClass);
                }
            } else {
                if (entityClass == null) {
                    q = ((Session) currentSession).createNativeQuery(sql);
                } else {
                    q = ((Session) currentSession).createNativeQuery(sql, entityClass);
                }
            }
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e, sql);
            } else {
                throw new SOSHibernateQueryException(e, sql);
            }
        } catch (IllegalArgumentException e) {
            throw new SOSHibernateQueryException(e, sql);
        } catch (PersistenceException e) {
            throw new SOSHibernateQueryException(e, sql);
        }
        return q;
    }

    public <T> T getSingleValue(String hql) throws SOSHibernateInvalidSessionException, SOSHibernateQueryNonUniqueResultException,
            SOSHibernateQueryException {
        return getSingleValue(createQuery(hql));
    }

    public <T> T getSingleValueNativeQuery(String sql) throws SOSHibernateInvalidSessionException, SOSHibernateQueryNonUniqueResultException,
            SOSHibernateQueryException {
        return getSingleValue(createNativeQuery(sql));
    }

    /** return a single field value or null
     * 
     * execute NativeQuery or Query
     * 
     * difference to Query.getSingleResult - not throw NoResultException, return single value as string */
    @SuppressWarnings("deprecation")
    public <T> T getSingleValue(Query<T> query) throws SOSHibernateInvalidSessionException, SOSHibernateQueryNonUniqueResultException,
            SOSHibernateQueryException {
        String method = getMethodName("getSingleValue");
        LOGGER.debug(String.format("%s: query[%s]", method, query.getQueryString()));
        T result = getSingleResult(query);
        if (result != null) {
            if (query instanceof NativeQuery<?>) {
                if (result instanceof Object[]) {
                    throw new SOSHibernateQueryNonUniqueResultException("query return a row and not a unique field result", query);
                }
            } else {
                if (result.getClass().getAnnotation(Entity.class) != null) {
                    throw new SOSHibernateQueryNonUniqueResultException("query return an entity object and not a unique field result", query);
                }
            }
            return result;
        }
        return null;
    }

    public <T> String getSingleValueAsString(String hql) throws SOSHibernateInvalidSessionException, SOSHibernateQueryNonUniqueResultException,
            SOSHibernateQueryException {
        T result = getSingleValue(hql);
        if (result != null) {
            return result + "";
        }
        return null;
    }

    public <T> String getSingleValueNativeQueryAsString(String sql) throws SOSHibernateInvalidSessionException,
            SOSHibernateQueryNonUniqueResultException, SOSHibernateQueryException {
        T result = getSingleValueNativeQuery(sql);
        if (result != null) {
            return result + "";
        }
        return null;
    }

    public <T> String getSingleValueAsString(Query<T> query) throws SOSHibernateInvalidSessionException, SOSHibernateQueryNonUniqueResultException,
            SOSHibernateQueryException {
        T result = getSingleValue(query);
        if (result != null) {
            return result + "";
        }
        return null;
    }

    public <T> T getSingleResult(String hql) throws SOSHibernateInvalidSessionException, SOSHibernateQueryNonUniqueResultException,
            SOSHibernateQueryException {
        return getSingleResult(createQuery(hql));
    }

    /** return a single row or null
     * 
     * execute NativeQuery or Query
     * 
     * difference to Query.getSingleResult - not throw NoResultException */
    @SuppressWarnings("deprecation")
    public <T> T getSingleResult(Query<T> query) throws SOSHibernateInvalidSessionException, SOSHibernateQueryNonUniqueResultException,
            SOSHibernateQueryException {
        String method = getMethodName("getSingleResult");
        LOGGER.debug(String.format("%s: query[%s]", method, query.getQueryString()));
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("session is NULL", query);
        }
        T result = null;
        try {
            result = query.getSingleResult();
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e, query);
            } else {
                throw new SOSHibernateQueryException(e, query);
            }
        } catch (NoResultException e) {
            result = null;
        } catch (NonUniqueResultException e) {
            throw new SOSHibernateQueryNonUniqueResultException(e, query);
        } catch (PersistenceException e) {
            throw new SOSHibernateQueryException(e, query);
        }
        return result;
    }

    public <T> Map<String, String> getSingleResultNativeQuery(String sql) throws SOSHibernateInvalidSessionException,
            SOSHibernateQueryNonUniqueResultException, SOSHibernateQueryException {
        return getSingleResultNativeQuery(sql, null);
    }

    public <T> Map<String, String> getSingleResultNativeQuery(String sql, String dateTimeFormat) throws SOSHibernateInvalidSessionException,
            SOSHibernateQueryNonUniqueResultException, SOSHibernateQueryException {
        return getSingleResult(createNativeQuery(sql), dateTimeFormat);
    }

    public <T> Map<String, String> getSingleResult(NativeQuery<T> query) throws SOSHibernateInvalidSessionException,
            SOSHibernateQueryNonUniqueResultException, SOSHibernateQueryException {
        return getSingleResult(query, null);
    }

    /** return a single row represented by Map<String,String> or null
     * 
     * Map - see getResultList */
    @SuppressWarnings({ "deprecation", "unchecked" })
    public Map<String, String> getSingleResult(NativeQuery<?> nativeQuery, String dateTimeFormat) throws SOSHibernateInvalidSessionException,
            SOSHibernateQueryNonUniqueResultException, SOSHibernateQueryException {
        String method = getMethodName("getSingleResult");
        LOGGER.debug(String.format("%s: nativeQuery[%s], dateTimeFormat=%s", method, nativeQuery.getQueryString(), dateTimeFormat));

        nativeQuery.setResultTransformer(getNativeQueryResultToMapTransformer(dateTimeFormat));
        Map<String, String> result = null;
        try {
            result = (Map<String, String>) nativeQuery.getSingleResult();
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e, nativeQuery);
            } else {
                throw new SOSHibernateQueryException(e, nativeQuery);
            }
        } catch (NoResultException e) {
            result = null;
        } catch (NonUniqueResultException e) {
            throw new SOSHibernateQueryNonUniqueResultException(e, nativeQuery);
        } catch (PersistenceException e) {
            throw new SOSHibernateQueryException(e, nativeQuery);
        }
        return result;
    }

    public <T> List<Map<String, String>> getResultListNativeQuery(String sql) throws SOSHibernateInvalidSessionException, SOSHibernateQueryException {
        return getResultListNativeQuery(sql, null);
    }

    public <T> List<Map<String, String>> getResultListNativeQuery(String sql, String dateTimeFormat) throws SOSHibernateInvalidSessionException,
            SOSHibernateQueryException {
        return getResultList(createNativeQuery(sql), dateTimeFormat);
    }

    public <T> List<Map<String, String>> getResultList(NativeQuery<T> query) throws SOSHibernateInvalidSessionException, SOSHibernateQueryException {
        return getResultList(query, null);
    }

    /** return a list of rows represented by Map<String,String>:
     * 
     * Map key - column name (lower case), Map value - value as string
     * 
     * 
     * setResultTransformer is deprecated (see below), but currently without alternative
     * 
     * excerpt from Query.setResultTransformer comment:
     * 
     * deprecated (since 5.2), todo develop a new approach to result transformers */
    @SuppressWarnings({ "deprecation", "unchecked" })
    public <T> List<Map<String, String>> getResultList(NativeQuery<T> nativeQuery, String dateTimeFormat) throws SOSHibernateInvalidSessionException,
            SOSHibernateQueryException {
        String method = getMethodName("getResultList");
        LOGGER.debug(String.format("%s: nativeQuery[%s], dateTimeFormat=%s", method, nativeQuery.getQueryString(), dateTimeFormat));
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("session is NULL", nativeQuery);
        }
        try {
            nativeQuery.setResultTransformer(getNativeQueryResultToMapTransformer(dateTimeFormat));
            return (List<Map<String, String>>) nativeQuery.getResultList();
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e, nativeQuery);
            } else {
                throw new SOSHibernateQueryException(e, nativeQuery);
            }
        } catch (PersistenceException e) {
            throw new SOSHibernateQueryException(e, nativeQuery);
        }
    }

    private ResultTransformer getNativeQueryResultToMapTransformer(String dateTimeFormat) {
        return new ResultTransformer() {

            private static final long serialVersionUID = 1L;

            @Override
            public Object transformTuple(Object[] tuple, String[] aliases) {
                Map<String, String> result = new HashMap<String, String>(tuple.length);
                for (int i = 0; i < tuple.length; i++) {
                    String alias = aliases[i];
                    if (alias != null) {
                        Object origValue = tuple[i];
                        String value = "";
                        if (origValue != null) {
                            value = origValue + "";
                            if (dateTimeFormat != null && origValue instanceof java.sql.Timestamp) {
                                try {
                                    value = SOSDate.getDateTimeAsString(value, dateTimeFormat);
                                } catch (Exception e) {
                                }
                            }
                        }
                        result.put(alias.toLowerCase(), value);
                    }
                }
                return result;
            }

            @SuppressWarnings("rawtypes")
            @Override
            public List<?> transformList(List collection) {
                return collection;
            }
        };
    }

    /** execute NativeQuery or Query */
    @SuppressWarnings("deprecation")
    public <T> List<T> getResultList(Query<T> query) throws SOSHibernateInvalidSessionException, SOSHibernateQueryException {
        String method = getMethodName("getResultList");
        LOGGER.debug(String.format("%s: query[%s]", method, query.getQueryString()));
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("session is NULL", query);
        }
        try {
            return query.getResultList();
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e, query);
            } else {
                throw new SOSHibernateQueryException(e, query);
            }
        } catch (PersistenceException e) {
            throw new SOSHibernateQueryException(e, query);
        }
    }

    public <T> List<T> getResultList(String hql) throws SOSHibernateInvalidSessionException, SOSHibernateQueryException {
        Query<T> query = createQuery(hql);
        return getResultList(query);
    }

    @Deprecated
    public Criteria createCriteria(Class<?> cl, String alias) throws SOSHibernateInvalidSessionException, SOSHibernateCriteriaException {
        String method = getMethodName("createCriteria");
        LOGGER.debug(String.format("%s: class=%s", method, cl.getSimpleName()));
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("session is NULL");
        }
        Criteria cr = null;
        try {
            if (isStatelessSession) {
                cr = ((StatelessSession) currentSession).createCriteria(cl, alias);
            } else {
                cr = ((Session) currentSession).createCriteria(cl, alias);
            }
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e);
            } else {
                throw new SOSHibernateCriteriaException(e);
            }
        } catch (PersistenceException e) {
            throw new SOSHibernateCriteriaException(e);
        }
        return cr;
    }

    @Deprecated
    public Criteria createCriteria(Class<?> cl) throws SOSHibernateInvalidSessionException, SOSHibernateCriteriaException {
        return createCriteria(cl, (String) null);
    }

    @Deprecated
    public Criteria createCriteria(Class<?> cl, String[] selectProperties) throws SOSHibernateInvalidSessionException, SOSHibernateCriteriaException {
        return createCriteria(cl, selectProperties, null);
    }

    @Deprecated
    public Criteria createCriteria(Class<?> cl, String[] selectProperties, ResultTransformer transformer) throws SOSHibernateInvalidSessionException,
            SOSHibernateCriteriaException {
        Criteria cr = createCriteria(cl);
        if (cr == null) {
            throw new SOSHibernateCriteriaException("criteria is NULL");
        }
        try {
            if (selectProperties != null) {
                ProjectionList pl = Projections.projectionList();
                for (String property : selectProperties) {
                    pl.add(Projections.property(property), property);
                }
                cr.setProjection(pl);
            }
            if (transformer != null) {
                cr.setResultTransformer(transformer);
            }
        } catch (IllegalStateException e) {
            if (e.getCause() == null) {
                throw new SOSHibernateInvalidSessionException(e);
            } else {
                throw new SOSHibernateCriteriaException(e);
            }
        } catch (PersistenceException e) {
            throw new SOSHibernateCriteriaException(e);
        }
        return cr;
    }

    @Deprecated
    public Criteria createSingleListTransform2BeanCriteria(Class<?> cl, String selectProperty) throws SOSHibernateInvalidSessionException,
            SOSHibernateCriteriaException {
        return createSingleListCriteria(cl, selectProperty, Transformers.aliasToBean(cl));
    }

    @Deprecated
    public Criteria createSingleListCriteria(Class<?> cl, String selectProperty) throws SOSHibernateInvalidSessionException,
            SOSHibernateCriteriaException {
        return createSingleListCriteria(cl, selectProperty, null);
    }

    @Deprecated
    public Criteria createSingleListCriteria(Class<?> cl, String selectProperty, ResultTransformer transformer)
            throws SOSHibernateInvalidSessionException, SOSHibernateCriteriaException {
        return createCriteria(cl, new String[] { selectProperty }, transformer);
    }

    @Deprecated
    public Criteria createTransform2BeanCriteria(Class<?> cl) throws SOSHibernateInvalidSessionException, SOSHibernateCriteriaException {
        return createCriteria(cl, null, null);
    }

    @Deprecated
    public Criteria createTransform2BeanCriteria(Class<?> cl, String[] selectProperties) throws SOSHibernateInvalidSessionException,
            SOSHibernateCriteriaException {
        return createCriteria(cl, selectProperties, Transformers.aliasToBean(cl));
    }

    public static Criterion createInCriterion(String propertyName, List<?> list) {
        Criterion criterion = null;
        int size = list.size();

        for (int i = 0; i < size; i += LIMIT_IN_CLAUSE) {
            List<?> subList;
            if (size > i + LIMIT_IN_CLAUSE) {
                subList = list.subList(i, (i + LIMIT_IN_CLAUSE));
            } else {
                subList = list.subList(i, size);
            }
            if (criterion != null) {
                criterion = Restrictions.or(criterion, Restrictions.in(propertyName, subList));
            } else {
                criterion = Restrictions.in(propertyName, subList);
            }
        }
        return criterion;
    }

    private String getMethodName(String name) {
        String prefix = identifier == null ? "" : String.format("[%s] ", identifier);
        return String.format("%s%s", prefix, name);
    }

    private void closeTransaction() {
        String method = getMethodName("closeTransaction");
        try {
            if (currentSession != null) {
                Transaction tr = getTransaction();
                if (tr != null) {
                    LOGGER.debug(String.format("%s: rollback", method));
                    tr.rollback();
                } else {
                    LOGGER.debug(String.format("%s: skip rollback (transaction is null)", method));
                }
            }
        } catch (Throwable ex) {
            //
        }

    }

    private void closeSession() {
        String method = getMethodName("closeSession");
        LOGGER.debug(String.format("%s", method));
        try {
            if (currentSession != null) {
                if (isStatelessSession) {
                    StatelessSession s = (StatelessSession) currentSession;
                    s.close();
                } else {
                    Session session = (Session) currentSession;
                    if (session.isOpen()) {
                        session.close();
                    }
                }
            }
        } catch (Throwable e) {
        }
        currentSession = null;
    }

}