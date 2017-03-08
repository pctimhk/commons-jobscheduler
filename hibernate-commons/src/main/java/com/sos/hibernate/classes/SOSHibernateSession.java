package com.sos.hibernate.classes;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.internal.SessionImpl;
import org.hibernate.internal.StatelessSessionImpl;
import org.hibernate.jdbc.Work;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sos.exception.DBSessionException;

public class SOSHibernateSession implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(SOSHibernateSession.class);
    private SOSHibernateFactory factory;
    private SessionFactory sessionFactory;
    private Object currentSession;
    private boolean useOpenStatelessSession;
    private boolean useGetCurrentSession;
    private FlushMode sessionFlushMode;
    private String identifier;
    private String openSessionMethodName;
    public static final int LIMIT_IN_CLAUSE = 1000;

    /** 
     * @deprecated
     * 
     * use factory.openSession() or factory.openStatelessSession(); 
     */
    @Deprecated
    public SOSHibernateSession(SOSHibernateFactory hibernateFactory) {
        this.factory = hibernateFactory;
        this.sessionFactory = hibernateFactory.getSessionFactory();
        initSessionProperties();
    }

    @Deprecated
    public void reconnect() throws Exception {
        String method = getMethodName("reconnect");
        try {
            LOGGER.info(String.format("%s: useOpenStatelessSession = %s", method, useOpenStatelessSession));
            disconnect();
            connect();
        } catch (Exception ex) {
            throw new Exception(String.format("%s: %s", method, ex.toString()), ex);
        }
    }

    /** 
     * @deprecated
     * 
     * use factory.openSession() or factory.openStatelessSession(); 
     */
    @Deprecated
    public void connect() throws Exception {
        String method = getMethodName("connect");
        try {
            openSession();
            String connFile = (factory.getConfigFile().isPresent()) ? factory.getConfigFile().get().toAbsolutePath().toString()
                    : "without config file";
            int isolationLevel = getFactory().getTransactionIsolation();
            LOGGER.debug(String.format("%s: autocommit = %s, transaction isolation = %s, %s, %s", method, getFactory().getAutoCommit(),
                    SOSHibernateFactory.getTransactionIsolationName(isolationLevel), openSessionMethodName, connFile));
        } catch (Exception ex) {
            throw new Exception(String.format("%s: %s", method, ex.toString()), ex);
        }
    }

    private void initSessionProperties() {
        sessionFlushMode = FlushMode.ALWAYS;
        useOpenStatelessSession = false;
        useGetCurrentSession = false;
    }

    protected void openSession() throws Exception {
        String method = getMethodName("openSession");

        if (this.currentSession != null) {
            LOGGER.debug(String.format("%s: close currentSession", method));
            closeSession();
        }

        LOGGER.debug(String.format("%s: useOpenStatelessSession = %s, useGetCurrentSession = %s", method, useOpenStatelessSession,
                useGetCurrentSession));

        openSessionMethodName = "";
        if (useOpenStatelessSession) {
            currentSession = sessionFactory.openStatelessSession();
            openSessionMethodName = "openStatelessSession";
        } else {
            Session session = null;
            if (useGetCurrentSession) {
                session = sessionFactory.getCurrentSession();
                openSessionMethodName = "getCurrentSession";
            } else {
                session = sessionFactory.openSession();
                openSessionMethodName = "openSession";
            }
            if (sessionFlushMode != null) {
                session.setHibernateFlushMode(sessionFlushMode);
            }
            currentSession = session;
        }
    }

    public static Throwable getException(Throwable ex) {
        if (ex instanceof SQLGrammarException) {
            SQLGrammarException sqlGrEx = (SQLGrammarException) ex;
            SQLException sqlEx = sqlGrEx.getSQLException();
            return new Exception(String.format("%s [exception: %s, sql: %s]", ex.getMessage(), sqlEx == null ? "" : sqlEx.getMessage(), sqlGrEx
                    .getSQL()), sqlEx);
        } else if (ex.getCause() != null) {
            return ex.getCause();
        }
        return ex;
    }

    /** 
     * @deprecated
     * 
     * use close(); 
     */
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

    public void clearSession() throws Exception {
        String method = getMethodName("clearSession");
        LOGGER.debug(String.format("%s", method));
        if (currentSession == null) {
            throw new DBSessionException("session is NULL");
        }
        if (currentSession instanceof Session) {
            Session session = (Session) currentSession;
            session.clear();
        }
    }

    public void sessionDoWork(Work work) throws Exception {
        String method = getMethodName("sessionDoWork");
        LOGGER.debug(String.format("%s", method));
        if (currentSession == null) {
            throw new DBSessionException("currentSession is NULL");
        }
        if (currentSession instanceof Session) {
            Session session = (Session) currentSession;
            session.doWork(work);
        } else {
            LOGGER.warn(String.format("%s: this method will be ignored for current openSessionMethodName : %s (%s)", method, openSessionMethodName,
                    currentSession.getClass().getSimpleName()));
        }
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
        } catch (Exception ex) {
            //
        }

    }

    public void closeSession() {
        String method = getMethodName("closeSession");
        LOGGER.debug(String.format("%s", method));
        try {
            if (currentSession != null) {
                if (currentSession instanceof Session) {
                    Session session = (Session) currentSession;
                    if (session.isOpen()) {
                        session.close();
                    }
                } else if (currentSession instanceof StatelessSession) {
                    StatelessSession s = (StatelessSession) currentSession;
                    s.close();
                }
            }
        } catch (Throwable e) {
        }
        currentSession = null;
        openSessionMethodName = null;
    }

    public void executeNativeQueries(Path file) throws Exception {
        executeNativeQueries(new String(Files.readAllBytes(file)));
    }

    public void executeNativeQueries(String content) throws Exception {
        SOSSqlCommandExtractor extractor = new SOSSqlCommandExtractor(this.factory.getDbms());
        try {
            beginTransaction();

            ArrayList<String> commands = extractor.extractCommands(content);
            for (int i = 0; i < commands.size(); i++) {
                NativeQuery<?> q = createNativeQuery(commands.get(i));
                if (isResultListQuery(commands.get(i))) {
                    q.getResultList();
                } else {
                    q.executeUpdate();
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            rollback();
        }
    }

    private boolean isResultListQuery(String statement) {
        String patterns = "^select|^exec";
        Pattern p = Pattern.compile(patterns, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(statement);
        return matcher.find();
    }

    public Query createQuery(String query) throws Exception {
        String method = getMethodName("createQuery");
        LOGGER.debug(String.format("%s: query = %s", method, query));
        if (currentSession == null) {
            throw new DBSessionException("Session is NULL");
        }
        Query q = null;
        if (currentSession instanceof Session) {
            q = ((Session) currentSession).createQuery(query);
        } else if (currentSession instanceof StatelessSession) {
            q = ((StatelessSession) currentSession).createQuery(query);
        }
        return q;
    }

    public NativeQuery createNativeQuery(String query) throws Exception {
        return createNativeQuery(query, null);
    }

    public NativeQuery createNativeQuery(String query, Class<?> entityClass) throws Exception {
        String method = getMethodName("createNativeQuery");
        LOGGER.debug(String.format("%s: query = %s", method, query));
        if (currentSession == null) {
            throw new DBSessionException("currentSession is NULL");
        }
        NativeQuery<?> q = null;
        if (currentSession instanceof Session) {
            if (entityClass == null) {
                q = ((Session) currentSession).createNativeQuery(query);
            } else {
                q = ((Session) currentSession).createNativeQuery(query, entityClass);
            }
        } else if (currentSession instanceof StatelessSession) {
            if (entityClass == null) {
                q = ((StatelessSession) currentSession).createNativeQuery(query);
            } else {
                q = ((StatelessSession) currentSession).createNativeQuery(query, entityClass);
            }
        }
        return q;
    }

    @Deprecated
    public SQLQuery<?> createSQLQuery(String query) throws Exception {
        return createSQLQuery(query, null);
    }

    @Deprecated
    public SQLQuery<?> createSQLQuery(String query, Class<?> entityClass) throws Exception {
        String method = getMethodName("createSQLQuery");
        LOGGER.debug(String.format("%s: query = %s", method, query));
        if (currentSession == null) {
            throw new DBSessionException("currentSession is NULL");
        }
        SQLQuery<?> q = null;
        if (currentSession instanceof Session) {
            q = ((Session) currentSession).createSQLQuery(query);
        } else if (currentSession instanceof StatelessSession) {
            q = ((StatelessSession) currentSession).createSQLQuery(query);
        }
        if (q != null && entityClass != null) {
            q.addEntity(entityClass);
        }
        return q;
    }

    @Deprecated
    public Criteria createCriteria(Class<?> cl, String alias) throws Exception {
        String method = getMethodName("createCriteria");
        LOGGER.debug(String.format("%s: class = %s", method, cl.getSimpleName()));
        if (currentSession == null) {
            throw new DBSessionException("currentSession is NULL");
        }
        Criteria cr = null;
        if (currentSession instanceof Session) {
            cr = ((Session) currentSession).createCriteria(cl, alias);
        } else if (currentSession instanceof StatelessSession) {
            cr = ((StatelessSession) currentSession).createCriteria(cl, alias);
        }
        return cr;
    }

    public Criteria createCriteria(Class<?> cl) throws Exception {
        return createCriteria(cl, (String) null);
    }

    public Criteria createCriteria(Class<?> cl, String[] selectProperties) throws Exception {
        return createCriteria(cl, selectProperties, null);
    }

    public Criteria createCriteria(Class<?> cl, String[] selectProperties, ResultTransformer transformer) throws Exception {
        Criteria cr = createCriteria(cl);
        if (cr == null) {
            throw new Exception("Criteria is NULL");
        }
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
        return cr;
    }

    public Criteria createSingleListTransform2BeanCriteria(Class<?> cl, String selectProperty) throws Exception {
        return createSingleListCriteria(cl, selectProperty, Transformers.aliasToBean(cl));
    }

    public Criteria createSingleListCriteria(Class<?> cl, String selectProperty) throws Exception {
        return createSingleListCriteria(cl, selectProperty, null);
    }

    public Criteria createSingleListCriteria(Class<?> cl, String selectProperty, ResultTransformer transformer) throws Exception {
        return createCriteria(cl, new String[] { selectProperty }, transformer);
    }

    public Criteria createTransform2BeanCriteria(Class<?> cl) throws Exception {
        return createCriteria(cl, null, null);
    }

    public Criteria createTransform2BeanCriteria(Class<?> cl, String[] selectProperties) throws Exception {
        return createCriteria(cl, selectProperties, Transformers.aliasToBean(cl));
    }

    public void beginTransaction() throws Exception {
        String method = getMethodName("beginTransaction");
        if (this.getFactory().getAutoCommit()) {
            LOGGER.debug(String.format("%s: skip (autoCommit is true)", method));
            return;
        }
        LOGGER.debug(String.format("%s", method));
        if (currentSession == null) {
            throw new DBSessionException("currentSession is NULL");
        }
        if (currentSession instanceof Session) {
            Session session = ((Session) currentSession);
            session.beginTransaction();
        } else if (currentSession instanceof StatelessSession) {
            StatelessSession session = ((StatelessSession) currentSession);
            session.beginTransaction();
        }
    }

    public Transaction getTransaction() throws Exception {
        Transaction tr = null;
        if (currentSession == null) {
            throw new DBSessionException("currentSession is NULL");
        }
        if (currentSession instanceof Session) {
            Session s = ((Session) currentSession);
            tr = s.getTransaction();
        } else if (currentSession instanceof StatelessSession) {
            StatelessSession s = ((StatelessSession) currentSession);
            tr = s.getTransaction();
        }
        return tr;
    }

    public void commit() throws Exception {
        String method = getMethodName("commit");
        if (this.getFactory().getAutoCommit()) {
            LOGGER.debug(String.format("%s: skip (autoCommit is true)", method));
            return;
        }
        LOGGER.debug(String.format("%s", method));
        Transaction tr = getTransaction();
        if (tr == null) {
            throw new Exception(String.format("session transaction is NULL"));
        }
        if (currentSession instanceof Session) {
            ((Session) currentSession).flush();
        }
        tr.commit();
    }

    public void rollback() throws Exception {
        String method = getMethodName("rollback");
        if (this.getFactory().getAutoCommit()) {
            LOGGER.debug(String.format("%s: skip (autoCommit is true)", method));
            return;
        }
        LOGGER.debug(String.format("%s", method));
        Transaction tr = getTransaction();
        if (tr == null) {
            throw new Exception(String.format("session transaction is NULL"));
        }
        tr.rollback();
    }

    public void save(Object item) throws Exception {
        String method = getMethodName("save");
        LOGGER.debug(String.format("%s: item = %s", method, item));
        if (currentSession == null) {
            throw new DBSessionException("session is NULL");
        }
        if (currentSession instanceof Session) {
            Session session = ((Session) currentSession);
            session.save(item);
            session.flush();
        } else if (currentSession instanceof StatelessSession) {
            StatelessSession session = ((StatelessSession) currentSession);
            session.insert(item);
        }
    }

    public void update(Object item) throws Exception {
        String method = getMethodName("update");
        LOGGER.debug(String.format("%s: item = %s", method, item));
        if (currentSession == null) {
            throw new DBSessionException("session is NULL");
        }
        if (currentSession instanceof Session) {
            Session session = ((Session) currentSession);
            session.update(item);
            session.flush();
        } else if (currentSession instanceof StatelessSession) {
            StatelessSession session = ((StatelessSession) currentSession);
            session.update(item);
        }
    }

    public Object saveOrUpdate(Object item) throws Exception {
        String method = getMethodName("saveOrUpdate");
        LOGGER.debug(String.format("%s: item = %s", method, item));
        if (currentSession == null) {
            throw new DBSessionException("currentSession is NULL");
        }
        if (currentSession instanceof Session) {
            Session session = ((Session) currentSession);
            session.saveOrUpdate(item);
            session.flush();
        } else if (currentSession instanceof StatelessSession) {
            StatelessSession session = ((StatelessSession) currentSession);
            /*
             * The following error will always be logged in the try segment, if the item id field is null: SQL Error: -1, SQLState: 07004 Parameter at position
             * 9 is not set HHH000010: On release of batch it still contained JDBC statements in a stateless session it is better to check if the item is a new
             * entry and then call save() or an existing item and then call update() there is no need to create an error to switch the statement afterwards
             */
            try {
                session.update(item);
            } catch (Exception e) {
                session.insert(item);
            }
        }
        return item;
    }

    public void delete(Object item) throws Exception {
        String method = getMethodName("delete");
        LOGGER.debug(String.format("%s: item = %s", method, item));
        if (currentSession == null) {
            throw new DBSessionException("currentSession is NULL");
        }
        if (currentSession instanceof Session) {
            Session session = ((Session) currentSession);
            session.delete(item);
            session.flush();
        } else if (currentSession instanceof StatelessSession) {
            StatelessSession session = ((StatelessSession) currentSession);
            session.delete(item);
        }
    }

    public Object getCurrentSession() throws Exception {
        return currentSession;
    }

    public Configuration getConfiguration() {
        return factory.getConfiguration();
    }

    public Connection getJdbcConnection() throws Exception {
        String method = "getJdbcConnection";
        if (currentSession instanceof Session) {
            SessionImpl sf = (SessionImpl) currentSession;
            try {
                return sf.connection();
            } catch (Exception e) {
                throw new Exception(String.format("%s: %s", method, e.toString()), e);
            }
        } else {
            StatelessSessionImpl sf = (StatelessSessionImpl) currentSession;
            try {
                return sf.connection();
            } catch (Exception e) {
                throw new Exception(String.format("%s: %s", method, e.toString()), e);
            }
        }
    }

    public FlushMode getSessionFlushMode() {
        return sessionFlushMode;
    }

    public void setSessionFlushMode(FlushMode sessionFlushMode) {
        this.sessionFlushMode = sessionFlushMode;
    }

    private String getMethodName(String name) {
        String prefix = identifier == null ? "" : String.format("[%s] ", identifier);
        return String.format("%s%s", prefix, name);
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

    public Object get(Class<?> entityClass, Serializable id) throws Exception {
        if (currentSession instanceof Session) {
            return ((Session) currentSession).get(entityClass, id);
        } else if (currentSession instanceof StatelessSession) {
            return ((StatelessSession) currentSession).get(entityClass, id);
        }
        return null;
    }

    public boolean isUseOpenStatelessSession() {
        return useOpenStatelessSession;
    }

    public void setUseOpenStatelessSession(boolean useOpenStatelessSession) {
        this.useOpenStatelessSession = useOpenStatelessSession;
    }

    public boolean isUseGetCurrentSession() {
        return useGetCurrentSession;
    }

    public void setUseGetCurrentSession(boolean useGetCurrentSession) {
        this.useGetCurrentSession = useGetCurrentSession;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String val) {
        identifier = val;
    }

    public SOSHibernateFactory getFactory() {
        return this.factory;
    }
}