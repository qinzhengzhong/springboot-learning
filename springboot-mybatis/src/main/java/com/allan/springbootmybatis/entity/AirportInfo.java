package com.allan.springbootmybatis.entity;

import java.math.BigDecimal;

public class AirportInfo {
    private Integer id;

    private String airportCode;

    private String airportEn;

    private String airportCn;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String cityCode;

    private String cityNameCn;

    private String country;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode == null ? null : airportCode.trim();
    }

    public String getAirportEn() {
        return airportEn;
    }

    public void setAirportEn(String airportEn) {
        this.airportEn = airportEn == null ? null : airportEn.trim();
    }

    public String getAirportCn() {
        return airportCn;
    }

    public void setAirportCn(String airportCn) {
        this.airportCn = airportCn == null ? null : airportCn.trim();
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCityNameCn() {
        return cityNameCn;
    }

    public void setCityNameCn(String cityNameCn) {
        this.cityNameCn = cityNameCn == null ? null : cityNameCn.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }
}