package ec.action;

import java.util.Date;


import javax.annotation.Resource;

import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import java.util.List;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.where.SimpleWhere;

import ec.entity.Usr;
import ec.service.UsrService;
import ec.form.UsrForm;

public class UsrAction {


    public List<Usr> usrItems;
    
    @ActionForm
    @Resource
    protected UsrForm usrForm;

    @Resource
    protected UsrService usrService;

    public JdbcManager jdbcManager;

    @Execute(validator = false)
    public String index() {
        usrItems = usrService.findAll();

        return "list.jsp";
    }





    @Execute(validator = false, urlPattern = "show/{usrId}")
    public String show() {
        Usr entity = usrService.findById(usrForm.usrId);
        Beans.copy(entity, usrForm).dateConverter("yyyy-MM-dd").execute();
        return "show.jsp";
    }

    @Execute(validator = false, urlPattern = "edit/{usrId}")
    public String edit() {
        Usr entity = usrService.findById(usrForm.usrId);
        Beans.copy(entity, usrForm).dateConverter("yyyy-MM-dd").execute();
        return "edit.jsp";
    }

    @Execute(validator = false)
    public String create() {
        return "create.jsp";
    }

    @Execute(validator = false, urlPattern = "delete/{usrId}", redirect = true)
    public String delete() {
        Usr entity = Beans.createAndCopy(Usr.class, usrForm).dateConverter("yyyy-MM-dd").execute();
        usrService.delete(entity);
        return "/usr/";
    }

    @Execute(input = "create.jsp", redirect = true)
    public String insert() {
        Usr entity = Beans.createAndCopy(Usr.class, usrForm).dateConverter("yyyy-MM-dd").execute();
        usrService.insert(entity);
        return "/usr/";
    }

    @Execute(input = "edit.jsp", redirect = true)
    public String update() {
        Usr entity = Beans.createAndCopy(Usr.class, usrForm).dateConverter("yyyy-MM-dd").execute();
        usrService.update(entity);
        return "/usr/";
    }
}