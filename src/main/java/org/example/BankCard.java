package org.example;

public class BankCard
{
    private String id_owner;
    private String numberCard;
    private String CSV;
    private String status;
    private String serviceLife;
    private String score;

    public BankCard (String numberCard, String CSV, String serviceLife)
    {
        this.numberCard = numberCard;
        this.CSV = CSV;
        this.serviceLife = serviceLife;
    }
    public BankCard (String status,String numberCard, String CSV, String serviceLife)
    {
        this.numberCard = numberCard;
        this.CSV = CSV;
        this.serviceLife = serviceLife;
        this.status = status;
    }
    public BankCard (String id_owner,String status,String numberCard, String CSV, String serviceLife)
    {
        this.numberCard = numberCard;
        this.CSV = CSV;
        this.serviceLife = serviceLife;
        this.status = status;
        this.id_owner = id_owner;
    }
    public BankCard (String score,String id_owner,String status,String numberCard, String CSV, String serviceLife)
    {
        this.numberCard = numberCard;
        this.CSV = CSV;
        this.serviceLife = serviceLife;
        this.status = status;
        this.id_owner = id_owner;
        this.score = score;
    }
    public boolean pay (String price)
    {
        if (!status.equals("overdue") || !status.equals("blocked"))
        {
            if (Integer.parseInt(price) > Integer.parseInt(score))
            {
                System.out.println("У вас не достаточно средст");
                return false;
            }
            else
            {
                score = Integer.toString(Integer.parseInt(score) - Integer.parseInt(price));
                return true;
            }
        }
        else
        {
            return false;
        }
    }
    public boolean transfer (String price, BankCard card)
    {

        if (!status.equals("overdue") || !status.equals("blocked"))
        {
            if (Integer.parseInt(price) > Integer.parseInt(score))
            {
                System.out.println("У вас не достаточно средств");
                return false;
            }
            else
            {
                card.score += price;
                score = Integer.toString(Integer.parseInt(score) - Integer.parseInt(price));
                return true;
            }
        }
        else
        {
            return false;
        }
    }
    public boolean replenish (String price)
    {
        if(!status.equals("overdue") || !status.equals("blocked"))
        {
            score += price;
            return true;
        }
        else
        {
            return false;
        }
    }
    public void statusCard (int i)
    {
        switch (i)
        {
            case (1): status = "bloced";
            case (2): status = "overdue";
            case (3): status = "unopened";
        }
    }

}