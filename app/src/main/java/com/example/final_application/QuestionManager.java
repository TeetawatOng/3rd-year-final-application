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