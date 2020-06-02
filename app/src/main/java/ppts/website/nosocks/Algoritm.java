package ppts.website.nosocks;

public class Algoritm {
    public String algoritm(String strn) {
        String[][] mass = new String[4][16];
        mass[1][0] = "1";
        mass[2][0] = "17";
        mass[3][0] = "33";
        //индексы картинок от 1 до 32
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 16; j++) {
                mass[i][j] = String.valueOf(Integer.parseInt(mass[i][j - 1]) + 1);
            }
        }

        //комбинации
        for (int i = 0; i < 16; i++) {
            int counter = i;
            char[] bit = {'0', '0', '0', '0'};
            for (int j = 0; j < 4; j++) {
                if (counter > 0) {
                    bit[j] = (counter % 2 == 0) ? '0' : '1';
                    counter = counter / 2;
                } else break;
            }
            for (int j = 0; j < 2; j++) {
                char temp = bit[j];
                bit[j] = bit[3 - j];
                bit[3 - j] = temp;
            }
            mass[0][i] = String.valueOf(bit);
        }

        String user_data = "";
        int randomize_number = 0;

        user_data = strn;

        //Сравнение введенных ответов с комбинацией
        for (int j = 0; j < 16; j++) {
            if (user_data.equals(mass[0][j])) {
                randomize_number = j;
                break;
            }
        }
        int random = (int) Math.round(1 + Math.random() * 2);

        return mass[random][randomize_number];
    }
}
