package com.example.mypc.musicwithgame.Model;

/**
 * Created by MyPC on 01/01/2018.
 */

public class CARS {
    private int user,banNhac,rate;

    public CARS(int user, int banNhac, int rate) {
        this.user = user;
        this.banNhac = banNhac;
        this.rate = rate;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getBanNhac() {
        return banNhac;
    }

    public void setBanNhac(int banNhac) {
        this.banNhac = banNhac;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
