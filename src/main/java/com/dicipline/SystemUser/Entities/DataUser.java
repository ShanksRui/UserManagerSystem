package com.dicipline.SystemUser.Entities;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;

@Entity
public class DataUser implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Transient
    private String cep;
    
    private String systemOperational;
    
    @Embedded
    private Localization localization = new Localization();
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private Instant login;
    private Instant loginClosed;
    
    @Column(name = "duration")
    private Duration duration;
       
    public DataUser() {}
    
    public DataUser(Long id, String systemOperational, String cep, Instant login, Instant loginClosed, User user,Duration duration) {
        this.id = id;
        this.systemOperational = 	systemOperational;
        this.localization.setCep(cep);
        this.login = login;
        this.loginClosed = loginClosed;
        this.user = user;
        this.duration = duration;
    }
     
      public Duration getDuration() {
    	  if(login != null && loginClosed != null) return duration = Duration.between(login, loginClosed);
		  return duration;
      }
      
      @PrePersist
      @PreUpdate
      private void calculateDuration() {
          if (login != null && loginClosed != null) {
              this.duration = Duration.between(login, loginClosed);
          }
      }
    
      public void setDuration(Duration duration) {
    	this.duration = duration;
    	
     }    
    public String getCep() {
        return this.cep;
    }
    
    public void setCep(String cep) {
        this.cep = cep;
    }
    
    public String getSystemOperational() {
        return this.systemOperational;
    }
    
    public void setSystemOperational(String systemOperational) {
        this.systemOperational = systemOperational;
    }
    
    public Instant getLogin() {
        return this.login;
    }
    
    public void setLogin(Instant login) {
        this.login = login;
    }
    
    public Instant getLoginClosed() {
        return this.loginClosed;
    }
    
    public void setLoginClosed(Instant loginClosed) {
        this.loginClosed = loginClosed;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Localization getLocalization() {
        return this.localization;
    }
    
    public void setLocalization(Localization localization) {
        this.localization = localization;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DataUser other = (DataUser) obj;
        return Objects.equals(id, other.id);
    }

	
}