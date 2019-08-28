package demo.service;



import demo.model.User;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {


  @PersistenceContext(unitName = "Default")
  private EntityManager entityManager;

  @Override
  public User getById(long id) {
    return entityManager.find(User.class,id);
  }
  @Override
  public User save(User user) {
    entityManager.persist(user);
    return user;
  }

}
