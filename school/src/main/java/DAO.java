import models.Parent;
import models.Region;
import models.School;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import utils.SessionUtil;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class DAO {
    public Session session;

    public DAO(){
        this.session = SessionUtil.getSessionFactory().openSession();
    }

    /**
     * Общие дао для всех сущностей
     */
    public <T> void save(T object){
        Transaction t = session.beginTransaction();
        try {
            session.save(object);
            t.commit();
        } catch (ConstraintViolationException e){
            t.rollback();
        }
    }

    public <T> void update(T object){
        Transaction t = session.beginTransaction();
        session.update(object);
        t.commit();
    }

    public <T> List<T> getAll(Class<T> type) {
        CriteriaQuery<T> criteria = session.getCriteriaBuilder().createQuery(type);
        criteria.from(type);
        return session.createQuery(criteria).getResultList();
    }

    /**
     * Отдельные дао по сущностям
     */
    //Поиск адреса по строке. В случае отсутствия адреса - добавляет его в бд
    public Region getRegionByName(String addr){
        try {
            Query query = session.createQuery("from Region where ad_name=:name");
            query.setParameter("name", addr);
            return (Region) query.getSingleResult();
        } catch (NoResultException e) {
            Region region = new Region(addr);
            save(region);
            return region;
        }
    }


    /**
     * Дополнительные методы работы с данными
     */
    //Обновление адреса школы
    public void updateSchoolAddress(School school, String address){
        school.setRegion(getRegionByName(address));
        update(school);
    }

    //Обновляем адрес родителя
    public void updateParentAddress(Parent parent, String address){
        parent.setRegion(getRegionByName(address));
        update(parent);
    }

}
