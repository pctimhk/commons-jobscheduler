package com.sos.hibernate.exceptions;

import javax.persistence.PersistenceException;

public class SOSHibernateTransactionException extends SOSHibernateException {

    private static final long serialVersionUID = 1L;

    public SOSHibernateTransactionException(String msg) {
        super(msg);
    }

    public SOSHibernateTransactionException(IllegalStateException cause) {
        super(cause);
    }

    public SOSHibernateTransactionException(IllegalStateException cause, String stmt) {
        super(cause, stmt);
    }

    public SOSHibernateTransactionException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public SOSHibernateTransactionException(PersistenceException cause) {
        super(cause);
    }
}
