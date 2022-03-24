package com.blog.userservice.Services;


import com.blog.userservice.Dao.IUserDao;
import com.blog.userservice.Domain.User;
import com.blog.userservice.VO.Post;
import com.blog.userservice.VO.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Resource
    private IUserDao userDao;

    @Autowired
    private RestTemplate restTemplate;


    public User add(User user){
        return userDao.save(user);
    }

    public List<User> getAll(){
        return userDao.findAll();
    }

    public void update(User user){
        userDao.save(user);
    }

    public void delete(Long id){
        userDao.deleteById(id);
    }

    public ResponseTemplateVO getUser(Long id) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userDao.findById(id).get();

        ResponseEntity<List<Post>> responseEntity = restTemplate.exchange(
                "http://POST-SERVICE/posts/",
                HttpMethod.GET,null,
                new ParameterizedTypeReference<List<Post>>(){});

        List<Post> posts = responseEntity.getBody()
                .stream().filter(item->item.getUserId()==id)
                .collect(Collectors.toList());
        vo.setUser(user);
        vo.setPost(posts);
        return vo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User loggedInUser = userDao.findByUsername(username);
        if (loggedInUser == null) {
            throw new UsernameNotFoundException("Invalid login credentials");
        }

        return  new org.springframework.security.core.userdetails.User(
                loggedInUser.getUsername(),
                loggedInUser.getPassword(),
                loggedInUser.getAuthorities()
                );
    }
}
