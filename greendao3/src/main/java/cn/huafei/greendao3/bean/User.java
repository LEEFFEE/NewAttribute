package cn.huafei.greendao3.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2016/11/24.
 */




@Entity
public class User{
    @Id
    private Long id;
    @Property(nameInDb = "USERNAME")
    private String username;
    @Property(nameInDb = "NICKNAME")
    private String nickname;
    @Transient
    private int tempCount;
    //@Transient，该注解表示这个属性将不会作为数据表中的一个字段。就是这么简单。另外还有一些比如
    // @NotNull表示该字段不可以为空，
    // @Unique表示该字段唯一。这里的注解还是挺多的，小伙伴们有兴趣可以自行研究
    @Generated(hash = 523935516)
    public User(Long id, String username, String nickname) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", tempCount=" + tempCount +
                '}';
    }
    //生成DaoMaster、DaoSession、DevOpenHelper  需要Build-->Make Project  或者  Make Mudule
}
