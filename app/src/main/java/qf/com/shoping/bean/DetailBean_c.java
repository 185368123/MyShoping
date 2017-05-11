package qf.com.shoping.bean;

import java.util.List;

/**
 * Created by li on 2017/2/22.
 */

public class DetailBean_c {

    String title;
    String nickname;
    String avatar;
    String pics;
    String likes;
    String content;
    String pic;
    String price;

    public DetailBean_c(String avatar, String content, String likes, String nickname, String pic, String pics, String price, String title) {
        this.avatar = avatar;
        this.content = content;
        this.likes = likes;
        this.nickname = nickname;
        this.pic = pic;
        this.pics = pics;
        this.price = price;
        this.title = title;
    }

    @Override
    public String toString() {
        return "DetailBean_c{" +
                "avatar='" + avatar + '\'' +
                ", title='" + title + '\'' +
                ", nickname='" + nickname + '\'' +
                ", pics='" + pics + '\'' +
                ", likes='" + likes + '\'' +
                ", content='" + content + '\'' +
                ", pic='" + pic + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public String getAvatar() {
        return avatar;
    }

    public String getContent() {
        return content;
    }

    public String getLikes() {
        return likes;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPic() {
        return pic;
    }

    public String getPics() {
        return pics;
    }

    public String getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }
}
