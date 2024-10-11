package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class RecipeFileHandler {
    private String filePath;//クラスのフィールド。レシピデータが保存されているファイルのパスを文字列として格納。

    public RecipeFileHandler() {//RecipeFileHandlerクラスの新しいインスタンスを作成するときに呼び出されます。このコンストラクタが呼ばれると、filePathフィールドにデフォルトのファイルパス（"app/src/main/resources/recipes.txt"）が設定される。
        filePath = "app/src/main/resources/recipes.txt";
    }

    public RecipeFileHandler(String filePath) {//ファイルパスを引数として受け取ります。インスタンスを作成するときに、特定のファイルパスを指定できるようにしている.
        this.filePath = filePath;
    }

    /**
     * 設問1: 一覧表示機能
     * recipes.txtからレシピデータを読み込み、それをリスト形式で返します。 <br>
     * IOExceptionが発生したときは<i>Error reading file: 例外のメッセージ</i>とコンソールに表示します。
     *
     * @return レシピデータ
     */
    public ArrayList<String> readRecipes() {
        ArrayList<String> recipes = new ArrayList<>();
        /*
         * ArrayList<String>はメソッドの戻り値の型を示している。このメソッドは、String型の要素を持つArrayListを返す。
         * readRecipesこのメソッドはレシピを読み込む処理を行うことを示唆している。
         * レシピデータが保存されているファイル（recipes.txt）からデータを読み取り、その内容をArrayList<String>に格納して返す。
         * ArrayList<String> recipes = new ArrayList<>();はrecipesという名前のArrayList<String>の新しいインスタンスを作成。初期状態ではこのリストは空。
         */

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) != null) {
                recipes.add(line);
            }
        } catch (IOException e) {
             System.out.println("Error reading file:" + e.getMessage());
        }
        return recipes;
    }
        /*
         * FileReaderは指定されたファイルを読み込むためのクラスです。ここでは、filePath（recipes.txtのパス）を指定してファイルを開く。
         * BufferedReaderは、文字入力ストリームにバッファを追加することで、効率的な読み込みを行うためのクラス
         * String line;はファイルから読み取った各行を格納するためのString型の変数
         * br.readLine(): このメソッドは、BufferedReaderオブジェクトから1行分のテキストを読み込みこむ。
         * whileループは、読み込んだ行がnullでない限り（つまり、ファイルの終わりでない限り）実行される。
         * recipes.add(line);は読み取った各行（line）を、先に作成したArrayList<String>（recipes）に追加する。
         * catch (IOException e)はtryブロック内でIOExceptionが発生した場合、このcatchブロックが実行される。
         * return recipesは最後に、読み込んだレシピデータが格納されたArrayList<String>（recipes）を返えす。
         */

    /**
     * 設問2: 新規登録機能
     * 新しいレシピをrecipes.txtに追加します。<br>
     * レシピ名と材料はカンマ区切りで1行としてファイルに書き込まれます。
     *
     * @param recipeName レシピ名
     * @param ingredients 材料名
     */
     //
    public void addRecipe(String recipeName, String ingredients) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
        // レシピ名と材料をカンマ区切りで1行として書き込む
        writer.write(recipeName + "," + ingredients);
        writer.newLine();  // 新しい行を追加
    } catch (IOException e) {
        System.out.println("Error writing to file: " + e.getMessage());
    }
    }
}
        /*
         * writer.newLine();はewLineメソッドは、ファイルに新しい行を追加する。これにより、次回の書き込みは新しい行から始まる。
         */


//動作内容
//readRecipes(): ファイルからレシピを一行ずつ読み込み、リストとして返す。ファイルが存在しない場合やエラーが発生した場合は、エラーメッセージを表示。
//addRecipe(): レシピ名と材料をカンマで区切ってファイルに追記。