package dao;


import domain.Player;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class PlayerDAOImpl implements PlayerDAO {

    @Autowired
    private HibernateTemplate template;

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    public void addOrUpdatePlayer(Player player){
        template.merge(player);
        template.flush();
    }
    public List<Player> getPlayerByName(String name){
        Query query = template.getSessionFactory().getCurrentSession().createQuery("FROM Player WHERE name = :plName ").setString("plName", name);
        List<Player> players = (List<Player>) query.list();
      return  players;
    }
    public Player getPlayerById(int id){
        Player player = (Player) template.get(Player.class, id);
        return player;
    }


}
