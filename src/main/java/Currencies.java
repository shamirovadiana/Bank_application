import java.util.Scanner;
import java.util.HashMap;


public class Currencies {

    private final HashMap<String, Double> Wallet = new HashMap<>();{
        Wallet.put("USD", 500.0);
        Wallet.put("EUR", 500.0);
        Wallet.put("RUB", 500.0);
        Wallet.put("UAH", 500.0);
        Wallet.put("JPY", 500.0);
        Wallet.put("UZS", 500.0);
    }

    private final HashMap<String,Double> USDToOtherMap = new HashMap<>();{
        USDToOtherMap.put("EUR", 0.97);
        USDToOtherMap.put("RUB", 59.43);
        USDToOtherMap.put("UAH", 6.70);
        USDToOtherMap.put("JPY", 135.89);
        USDToOtherMap.put("UZS", 10855.23);
        USDToOtherMap.put("USD", 1.0);
    }

    public Double convertCurrency(){
        Double Result = 0.0;
        Double Coef1;
        Double Coef2;
        Double OutputSum;
        Double InputSum;
        Double Commission = 0.01;
        Double Answer;
        String mess = "Do you want to continue your operations?\nWrite '1' if 'Yes' or '0' if 'No'";
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the currency of your money: ");
        String CurrentCurrency = in.nextLine();
        System.out.println("Enter the amount of your money: ");
        Double Sum = Double.parseDouble(in.nextLine());
        System.out.println("Enter the currency you need to convert to: ");
        String ConvertationCurrency = in.nextLine();

        if (CurrentCurrency.equals(ConvertationCurrency)) {
            System.out.println("You are converting " + CurrentCurrency + " to " + ConvertationCurrency + " !Please, change your current or convertation currency!");
            System.out.println();
        } else if (Wallet.get(CurrentCurrency) < Sum) {
            System.out.println("You do not have enough money on your account to convert this sum!");
            System.out.println();
        } else {
            Coef1 = 1 / USDToOtherMap.get(CurrentCurrency);
            Coef2 = USDToOtherMap.get(ConvertationCurrency);
            Result = Sum * Coef1 * Coef2;
            OutputSum = Wallet.get(CurrentCurrency) - (1.0 + Commission) * Sum;
            Wallet.put(CurrentCurrency, OutputSum);
            InputSum = Wallet.get(ConvertationCurrency) + Result;
            Wallet.put(ConvertationCurrency, InputSum);
            System.out.println(Sum + " " + CurrentCurrency + " = " + Result + " " + ConvertationCurrency);
            System.out.println(CurrentCurrency + " Wallet : " + OutputSum + " " + CurrentCurrency + "  |  " + ConvertationCurrency + " Wallet : " + InputSum + " " + ConvertationCurrency);
            System.out.println();
        }
        System.out.println(mess);
        Answer = Double.parseDouble(in.nextLine());
        if (Answer == 1){
            convertCurrency();
        }else {
            System.out.println("Operations completed successfully!");
        }
        return  Result;
        }
    }


