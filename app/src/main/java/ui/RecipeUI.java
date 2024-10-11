package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import data.RecipeFileHandler;

public class RecipeUI {
    private BufferedReader reader;
    private RecipeFileHandler fileHandler;

    public RecipeUI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fileHandler = new RecipeFileHandler();
    }

    public RecipeUI(BufferedReader reader, RecipeFileHandler fileHandler) {
        this.reader = reader;
        this.fileHandler = fileHandler;
    }

    public void displayMenu() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes(); // 設問1: 一覧表示機能
                        //recipes.txtファイルからレシピデータを読み込み出力。
                        break;
                    case "2":
                    try { // 設問2: 新規登録機能
                        addNewRecipe(); // 設問2: 新規登録機能
                        //addNewRecipeメソッドが呼び出され、ユーザーからレシピ名と材料を入力させ、RecipeFileHandlerを使用してそれらをrecipes.txtに追加。
                        } catch (IOException e) {
                        System.out.println("Error adding new recipe: " + e.getMessage());
                        }
                        break;
                    case "3":
                        // 設問3: 検索機能
                        break;
                    case "4":
                        System.out.println("Exit the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    /**
     * 設問1: 一覧表示機能
     * RecipeFileHandlerから読み込んだレシピデータを整形してコンソールに表示します。
     */
    private void displayRecipes() {
        ArrayList<String> recipes = fileHandler.readRecipes();  // ファイルからレシピを読み込む

        if (recipes.isEmpty()) {
            System.out.println("No recipes available.");
        } else {
            System.out.println("Recipes:");
            for (String recipe : recipes) {
                String[] parts = recipe.split(",", 2);  // レシピ名と材料を分割
                if (parts.length == 2) {
                    System.out.println("-----------------------------------");
                    System.out.println("Recipe Name: " + parts[0]);
                    System.out.println("Main Ingredients: " + parts[1]);
                }
            }
        }
    }
        /*
         * recipes.txtファイルからレシピを読み込み、その内容をコンソールに表示。
         * レシピが存在しない場合はメッセージを表示し、
         * 存在する場合は各レシピの名前と材料を整形して出力。
         */

    /**
     * 設問2: 新規登録機能
     * ユーザーからレシピ名と主な材料を入力させ、RecipeFileHandlerを使用してrecipes.txtに新しいレシピを追加します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void addNewRecipe() throws IOException {

    System.out.print("Enter recipe name: ");
    String recipeName = reader.readLine();
    // ユーザーからレシピ名を入力
    
    System.out.print("Enter main ingredients (comma separated): ");
    String ingredients = reader.readLine();
    // ユーザーから主な材料を入力（カンマ区切り）

    fileHandler.addRecipe(recipeName, ingredients);
    // RecipeFileHandlerを使用してレシピをファイルに追加

    System.out.println("Recipe added successfully.");
    // レシピが追加されたことを通知
    }
    /*
     * レシピ名と主な材料を受け取り、それをrecipes.txtファイルに追加。
     * ユーザーが入力した情報を取得しファイルへの追加処理を行った後、
     * 成功メッセージを表示することで、ユーザーの操作が完了したことを通知。
     */
    
    /**
     * 設問3: 検索機能
     * ユーザーから検索クエリを入力させ、そのクエリに基づいてレシピを検索し、一致するレシピをコンソールに表示します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void searchRecipe() throws IOException {

    }

}

