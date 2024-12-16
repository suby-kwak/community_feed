package org.fastcampus.post.domain;

import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Content getContent() {
        return content;
    }

    public int getLikeCount() {
        return likeCount.getCount();
    }

    public PostPublicationState getState() {
        return state;
    }

    public Post(Long id, User author, String content, PostPublicationState state) {
        if (author == null) {
            throw new IllegalArgumentException();
        }
        if (content == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = new PostContent(content);
        this.state = state;
        this.likeCount = new PositiveIntegerCounter();
    }

    public void like(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        likeCount.increase();
    }

    public void unlike(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        likeCount.decrease();
    }

    public void updatePost(User user, String updateContent) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.content.updateContent(updateContent);
    }

    public void updateState(PostPublicationState state) {
        this.state = state;
    }
}
