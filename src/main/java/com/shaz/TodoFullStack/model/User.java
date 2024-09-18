package com.shaz.TodoFullStack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "auth_provider")
    private String authProvider;

    public User(int id, String username, String password, String authProvider)
	{
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.authProvider=authProvider;
	}
    public User(String username, String password, String authProvider)
    {
    	this.username = username;
    	this.password = password;
    	this.authProvider=authProvider;
    }
    public User(){}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
	public String getAuthProvider()
	{
		return authProvider;
	}
	public void setAuthProvider(String authProvider)
	{
		this.authProvider = authProvider;
	}
	@Override
	public String toString()
	{
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", authProvider=" + authProvider
				+ "]";
	}

    
}