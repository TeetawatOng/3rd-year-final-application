package com.example.final_application;

public class QuestionManager {

    public static String question1[] = {
            "ข้อที่ 1: ข้อใดเป็นชนิดข้อมูลพื้นฐานใน JavaScript ที่ใช้เก็บตัวเลข?",
            "ข้อที่ 2: ข้อใดเป็นวิธีการประกาศตัวแปรที่สามารถเปลี่ยนแปลงค่าได้ในภายหลัง?",
            "ข้อที่ 3: ชนิดข้อมูลใดใน JavaScript ที่มีค่าเพียงสองค่าเท่านั้น คือ `true` และ `false`?",
            "ข้อที่ 4: ตัวแปรที่ถูกประกาศแต่ยังไม่ได้กำหนดค่าเริ่มต้น จะมีชนิดข้อมูลเป็นอะไร?",
            "ข้อที่ 5: ข้อใดเป็นวิธีที่ถูกต้องในการประกาศตัวแปรที่เก็บข้อความใน JavaScript?",
            "ข้อที่ 6: ชนิดข้อมูลใดใน JavaScript ที่ใช้เก็บกลุ่มของข้อมูลในรูปแบบ 'คีย์-ค่า'?",
            "ข้อที่ 7: ข้อใดเป็นชนิดข้อมูลที่ใช้เก็บรายการของข้อมูลหลายๆ ค่าเรียงกัน?",
            "ข้อที่ 8: ค่า `null` ใน JavaScript มีชนิดข้อมูลเป็นอะไรเมื่อใช้ `typeof` ตรวจสอบ?",
            "ข้อที่ 9: ข้อใดเป็นตัวอย่างของ 'String' ใน JavaScript?",
            "ข้อที่ 10: ตัวแปรที่ประกาศด้วยคำสั่งใดต่อไปนี้ จะมีค่าคงที่ ไม่สามารถเปลี่ยนแปลงค่าได้หลังจากกำหนดค่าเริ่มต้น?",
    };

    // choices
    public static String choices1[][] = {
            {"String", "Number", "Boolean", "Object"},
            {"const", "final", "var", "let"},
            {"Text", "Integer", "Boolean", "Character"},
            {"Null", "Undefined", "NaN", "0"},
            {"int message = \"Hello\";", "string message = 'World';", "let message = `Hello World`;", "char message = 'H';"},
            {"Array", "Tuple", "Object", "Set"},
            {"Object", "Array", "String", "Number"},
            {"number", "string", "object", "null"},
            {"123", "true", "`Hello World`", "{ key: 'value' }"},
            {"var", "let", "const", "variable"},
    };

    // answer index is (0, 1, 2, 3)
    public static int answer1[] = {
            1,
            3,
            2,
            1,
            2,
            2,
            1,
            2,
            2,
            2,
    };

    public static String question2[] = {
            "ข้อที่ 1: คำสั่งใดใช้สร้างตัวแปรที่ไม่สามารถเปลี่ยนค่าได้?",
            "ข้อที่ 2: ผลลัพธ์ของโค้ดนี้คืออะไร?\n" +
                    "console.log(a);  \n" +
                    "var a = 5;",
            "ข้อที่ 3: คำสั่งใดผิดพลาดใน JavaScript?",
            "ข้อที่ 4: ขอบเขตของตัวแปร let คืออะไร?",
            "ข้อที่ 5: ข้อใดเป็นตัวแปรที่ถูกต้อง?",
            "ข้อที่ 6: คำสั่งใดที่สามารถเปลี่ยนค่าของตัวแปรได้?",
            "ข้อที่ 7: ผลลัพธ์ของโค้ดนี้คืออะไร? \n" +
                    "{ let a = 100; }\n" +
                    "console.log(a);",
            "ข้อที่ 8: คำสั่งใดที่ไม่สามารถใช้ได้ใน JavaScript?",
            "ข้อที่ 9: ข้อใดเป็นผลลัพธ์ของโค้ดต่อไปนี้? \n"+
                    "let x = 10;" + "const y = 5;\n" +
                    "x = x + y;\n" +
                    "console.log(x);",
            "ข้อที่ 10: ผลลัพธ์ของโค้ดนี้จะเป็นอะไร? \n" +
                    "let x = 10;" + "const y = 5;\n" +
                    "y = x + y;\n" +
                    "console.log(y);",
    };

    // choices
    public static String choices2[][] = {
            {"var myVar = 10;", "let myVar = 10", "const myVar = 10", "variable myVar = 10"},
            {"undefined", "5", "ReferenceError", "null"},
            {"let x = 10", "const y;", "var z = \"Hello\";", "let a = 20;"},
            {"Global Scope", "Function Scope", "Block Scope", "ไม่มีขอบเขต"},
            {"var 2name = \"John\";", "let _userName = \"Doe\";", "const my-var = 50;", "let let = 30;"},
            {"const x = 10; x = 20;", "let x = 10; x = 20;", "var x = 10; const x = 20;", "let x = 10; let x = 20;"},
            {"100", "undefined", "ReferenceError", "null"},
            {"const pi = 3.14;", "var let = 10;", "let userName = \"Alice\";", "var x = 100;"},
            {"5", "10", "`15`", "Error! เปลี่ยนค่า const ไม่ได้"},
            {"5", "10", "`15`", "Error! เปลี่ยนค่า const ไม่ได้"},
    };

    // answer index is (0, 1, 2, 3)
    public static int answer2[] = {
            2, // "const myVar = 10"
            0, // "undefined (เพราะ Hoisting)"
            1, // "const y; (เพราะ const ต้องกำหนดค่าตั้งแต่ประกาศ)"
            2, // "Block Scope"
            1, // "let _userName = "Doe";"
            1, // "let x = 10; x = 20;"
            2, // "ReferenceError"
            1, // "var let = 10;"
            2, // "15"
            2, // "15"
    };

    public static String question3[] = {
            "ข้อที่ 1: ผลลัพธ์ของ console.log(5 + \"5\") คืออะไร?",
            "ข้อที่ 2: ผลลัพธ์ของ console.log(5 == \"5\") คืออะไร?",
            "ข้อที่ 3: ข้อใดให้ผลลัพธ์เป็น false?",
            "ข้อที่ 4: ผลลัพธ์ของ console.log(10 % 3) คืออะไร?",
            "ข้อที่ 5: ตัวดำเนินการใดที่ใช้สำหรับยกกำลัง?",
            "ข้อที่ 6: ค่าของ x หลังจาก let x = 10; x += 5; คืออะไร?",
            "ข้อที่ 7: console.log(!!0) จะได้ค่าอะไร?",
            "ข้อที่ 8: ผลลัพธ์ของ console.log(\"10\" - 2) คืออะไร?",
            "ข้อที่ 9: console.log(5 > 3 && 10 < 2) ให้ค่าอะไร?",
            "ข้อที่ 10: ตัวดำเนินการ x-- มีค่าเท่ากับข้อใด?" ,
    };

    // choices
    public static String choices3[][] = {
            {"10", "\"55\"", "5", "NaN"},
            {"true", "false", "undefined", "NaN"},
            {"5 > 3 && 2 < 4", "5 == \"5\"", "5 === \"5\"", "true || false"},
            {"3", "1", "0", "NaN"},
            {"^", "**", "//", "%"},
            {"5", "10", "15", "20"},
            {"true", "false", "undefined", "NaN"},
            {"\"102\"", "8", "10", "NaN"},
            {"true", "false", "undefined", "NaN"},
            {"x = x + 1", "x = x - 1", "x += 1", "x -= 2"},
    };

    // answer index is (0, 1, 2, 3)
    public static int answer3[] = {
            1, // "55" (เนื่องจาก + ใช้รวม string และ number)
            0, // true (เพราะ == ไม่สนใจชนิดข้อมูล)
            2, //  5 === "5" (เนื่องจาก === เช็คชนิดข้อมูลด้วย)
            1, // 1 (เพราะ 10 หาร 3 เหลือเศษ 1)
            1, // ** (ใช้สำหรับยกกำลัง เช่น 2 ** 3 ได้ 8)
            2, // 15 (เพราะ x += 5 เทียบเท่ากับ x = x + 5)
            1, // false (เพราะ 0 เป็น falsy และ !!0 แปลงเป็นบูลีน)
            1, // 8 (เพราะ - แปลง string เป็น number)
            1, // false (เพราะ 10 < 2 เป็น false)
            1, // x = x - 1 (เพราะ x-- ลดค่า x ลง 1)
    };

    public static String question4[] = {
            "ข้อที่ 1: ข้อใดเป็นชนิดข้อมูลพื้นฐานใน JavaScript ที่ใช้เก็บตัวเลข?",
            "ข้อที่ 2: ข้อใดเป็นวิธีการประกาศตัวแปรที่สามารถเปลี่ยนแปลงค่าได้ในภายหลัง?",
            "ข้อที่ 3: ชนิดข้อมูลใดใน JavaScript ที่มีค่าเพียงสองค่าเท่านั้น คือ `true` และ `false`?",
            "ข้อที่ 4: ตัวแปรที่ถูกประกาศแต่ยังไม่ได้กำหนดค่าเริ่มต้น จะมีชนิดข้อมูลเป็นอะไร?",
            "ข้อที่ 5: ข้อใดเป็นวิธีที่ถูกต้องในการประกาศตัวแปรที่เก็บข้อความใน JavaScript?",
            "ข้อที่ 6: ชนิดข้อมูลใดใน JavaScript ที่ใช้เก็บกลุ่มของข้อมูลในรูปแบบ 'คีย์-ค่า'?",
            "ข้อที่ 7: ข้อใดเป็นชนิดข้อมูลที่ใช้เก็บรายการของข้อมูลหลายๆ ค่าเรียงกัน?",
            "ข้อที่ 8: ค่า `null` ใน JavaScript มีชนิดข้อมูลเป็นอะไรเมื่อใช้ `typeof` ตรวจสอบ?",
            "ข้อที่ 9: ข้อใดเป็นตัวอย่างของ 'String' ใน JavaScript?",
            "ข้อที่ 10: ตัวแปรที่ประกาศด้วยคำสั่งใดต่อไปนี้ จะมีค่าคงที่ ไม่สามารถเปลี่ยนแปลงค่าได้หลังจากกำหนดค่าเริ่มต้น?",
    };

    // choices
    public static String choices4[][] = {
            {"String", "Number", "Boolean", "Object"},
            {"const", "final", "var", "let"},
            {"Text", "Integer", "Boolean", "Character"},
            {"Null", "Undefined", "NaN", "0"},
            {"int message = \"Hello\";", "string message = 'World';", "let message = `Hello World`;", "char message = 'H';"},
            {"Array", "Tuple", "Object", "Set"},
            {"Object", "Array", "String", "Number"},
            {"number", "string", "object", "null"},
            {"123", "true", "`Hello World`", "{ key: 'value' }"},
            {"var", "let", "const", "variable"},
    };

    // answer index is (0, 1, 2, 3)
    public static int answer4[] = {
            1, // "Number" อยู่ที่ index 1 ของ choices1[0]
            3, // "let" อยู่ที่ index 3 ของ choices1[1]
            2, // "Boolean" อยู่ที่ index 2 ของ choices1[2]
            1, // "Undefined" อยู่ที่ index 1 ของ choices1[3]
            2, // "let message = `Hello World`;" อยู่ที่ index 2 ของ choices1[4]
            2, // "Object" อยู่ที่ index 2 ของ choices1[5]
            1, // "Array" อยู่ที่ index 1 ของ choices1[6]
            2, // "object" อยู่ที่ index 2 ของ choices1[7]
            2, // "`Hello World`" อยู่ที่ index 2 ของ choices1[8]
            2, // "const" อยู่ที่ index 2 ของ choices1[9]
    };

}