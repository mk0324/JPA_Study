package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.MemberType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {   // 트랜젝션 시 문제에 따라 롤백이 발생하는 것 처리
            Member member = new Member();
            //member.setId(100L);
            member.setName("안녕하세요");
            member.setAge(20);
            member.setMemberType(MemberType.USER);

            em.persist(member);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        System.out.println("Hello");
        emf.close();
    }
}
