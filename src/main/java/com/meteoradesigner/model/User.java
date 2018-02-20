package com.meteoradesigner.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.SerializationUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Class implements the user entity.
 */
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractNamedEntity {
    //Can be several equal external tasks that doesn't lead to cycle.

    @NotBlank
    @Email
    @Size(min = 5, max = 6400)
    private String mail;

    @Size(max = 6433)
    private String password;

    @NotNull
    private LocalDateTime registrationTime = LocalDateTime.now();

    @NotEmpty
    private Set<Role> roles;

    private Set<TaskContext> contexts;

    private Set<TaskPortfolio> portfolios;

    private Set<Task> tasks;

    /**
     * Deep copying constructor.
     */
    public User(User user) {
        User userClone = SerializationUtils.clone(user);
        userClone.setId(0);
        new User(userClone.getId(), userClone.getDisplayName(), userClone.getMail(), userClone
                .getPassword(), userClone.getContexts(), userClone.getPortfolios(), userClone
                .getTasks());
    }

    /**
     * The all-args constructor.
     */
    public User(Integer idToSet, String displayNameToSet, String mail, String password,
                Set<TaskContext> contexts, Set<TaskPortfolio> portfolios, Set<Task> tasks) {
        super(idToSet, displayNameToSet);
        this.mail = mail;
        this.password = password;
        this.contexts = contexts;
        this.portfolios = portfolios;
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "User{" +
                "mail='" + mail + '\'' +
                ", registrationTime=" + registrationTime +
                ", roles=" + roles +
                ", contexts=" + contexts +
                ", portfolios=" + portfolios +
                ", tasks=" + tasks +
                '}';
    }
}