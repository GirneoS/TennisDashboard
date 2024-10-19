package com.ozhegov.tennis.dao;

import com.ozhegov.tennis.model.MatchDTO;
import com.ozhegov.tennis.model.Player;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PlayerDAO {
    public Player getByName(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Player> cq = cb.createQuery(Player.class);

        Root<Player> root = cq.from(Player.class);

        cq.select(root).where(cb.equal(root.get("name"),name));

        Query<Player> query = session.createQuery(cq);
        Player player = query.getSingleResult();

        session.close();
        return player;
    }
}
