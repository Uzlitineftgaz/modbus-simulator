import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class LetterCombinations2 {
    public static int counter = 0;

    public static void main(String[] args) {
        String letters = "dd067e0dc62800003714";
        generateAndPrintCombinations(letters, 8);
        System.out.println(counter);
    }

    public static void generateAndPrintCombinations(String letters, int combinationLength) {
        generateAndPrintCombinationsHelper(letters, combinationLength, "", 0);
    }

    public static void generateAndPrintCombinationsHelper(String letters, int combinationLength, String currentCombination, int startIndex) {
        float v = 0;
        counter++;
        if (currentCombination.length() == combinationLength) {
            v = hexToFloat(currentCombination);
            if (v>1.0&&v<13.0){
                System.out.println(currentCombination);

            }
//            System.out.println(currentCombination);
//            System.out.println(v);
            return;
        }

        for (int i = startIndex; i < letters.length(); i++) {
            String newCombination = currentCombination + letters.charAt(i);
            generateAndPrintCombinationsHelper(letters, combinationLength, newCombination, i + 1);
        }


    }

    public static float hexToFloat(String hexString) {
        byte[] bytes = new byte[hexString.length() / 2];

        for (int i = 0; i < bytes.length; i++) {
            int index = i * 2;
            bytes[i] = (byte) Integer.parseInt(hexString.substring(index, index + 2), 16);
        }

        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        float value = buffer.getFloat();
        return value;
    }
}