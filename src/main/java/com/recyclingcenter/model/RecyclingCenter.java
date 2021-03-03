package com.recyclingcenter.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class RecyclingCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;
    @NotEmpty
    private String localAuthority;
    @NotEmpty
    private String email;

    @NotEmpty
    private String url;

    private long phone;
    @NotEmpty
    private String postalCode;

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    private String collectionDay;

    //no argument constructor

    public RecyclingCenter() {
    }

    public RecyclingCenter(@NotEmpty String name, @NotEmpty String localAuthority, @NotEmpty String email, @NotEmpty String url, long phone, @NotEmpty String postalCode, Location location, String collectionDay) {
        this.name = name;
        this.localAuthority = localAuthority;
        this.email = email;
        this.url = url;
        this.phone = phone;
        this.postalCode = postalCode;
        this.location = location;
        this.collectionDay = collectionDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalAuthority() {
        return localAuthority;
    }

    public void setLocalAuthority(String localAuthority) {
        this.localAuthority = localAuthority;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getCollectionDay() {
        return collectionDay;
    }

    public void setCollectionDay(String collectionDate) {
        this.collectionDay = collectionDate;
    }

}
