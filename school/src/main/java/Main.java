import models.Children;
import models.Parent;
import models.Region;
import models.School;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DAO dao = new DAO();
        System.out.println("Добавляем адреса:");
        dao.save(new Region("МО, г.Клин, ул. Центральная"));
        dao.save(new Region("Москва, проспект Мира, д.5"));
        dao.save(new Region("Москва, 2-я Бауманская, д.5"));

        List<Region> regions = dao.getAll(Region.class);
        for (int i=0; i< regions.size(); i++){
            System.out.println(regions.get(i));
        }
        Region r1 = regions.get(0);
        Region r2 = regions.get(1);

        System.out.println("\nДобавляем школы:");
        School school = new School("Средняя школа №1", r1);
        dao.save(school);
        dao.save(new School("Лицей №2", r2));

        for(School s : dao.getAll(School.class)){
            System.out.println(s);
        }

        System.out.println("\nМеняем адрес для школы: " + school);
        dao.updateSchoolAddress(school, " г. Красногорск, д.15");
        System.out.println("Обновленная информация: " + school);

        System.out.println("\nДобавим семью");
        r1 = new Region("ул. Невеселова, д.5");
        dao.save(r1);
        dao.save(new Parent("Васильков Михаил", r1));
        dao.save(new Parent("Василькова Дарья", r1));
        List<Parent> parents = dao.getAll(Parent.class);

        for(Parent p : parents){
            System.out.println(p);
        }
        System.out.println("\nСменим адрес проживания одного из родителей");
        dao.updateParentAddress(parents.get(0), "ул. Веселая, д.22");
        System.out.println(parents.get(0));

        System.out.println("\nДобавим ребенка");
        Children child = new Children("Васильков Евгений", 13, parents);
        dao.save(child);
        System.out.println(child);

        System.out.println("\nДобавляем школу(выбираем рандомную из выведенного ниже списка)");
        List<School> schools = dao.getAll(School.class);
        for(School s: schools){
            System.out.println(s);
        }
        int selectedNumber = (int)(Math.random() * schools.size());
        School selectedSchool =  schools.get(selectedNumber);
        child.setSchool(selectedSchool);
        dao.update(child);
        System.out.println(child);

        dao.session.close();
    }
}
