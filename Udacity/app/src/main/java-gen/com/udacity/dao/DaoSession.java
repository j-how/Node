package com.udacity.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.udacity.bean.BeanMovie;

import com.udacity.dao.BeanMovieDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig beanMovieDaoConfig;

    private final BeanMovieDao beanMovieDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        beanMovieDaoConfig = daoConfigMap.get(BeanMovieDao.class).clone();
        beanMovieDaoConfig.initIdentityScope(type);

        beanMovieDao = new BeanMovieDao(beanMovieDaoConfig, this);

        registerDao(BeanMovie.class, beanMovieDao);
    }
    
    public void clear() {
        beanMovieDaoConfig.clearIdentityScope();
    }

    public BeanMovieDao getBeanMovieDao() {
        return beanMovieDao;
    }

}
