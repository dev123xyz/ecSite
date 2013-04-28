package ec.service;

import ec.entity.Usr;

public class UsrService extends AbstractService<Usr> {

    public Usr findById( String usrId ) {
        return select().id( usrId ).getSingleResult();
    }
}