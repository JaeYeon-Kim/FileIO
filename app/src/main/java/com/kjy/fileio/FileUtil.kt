package com.kjy.fileio

import java.io.*


// 내부저장소 파일 읽기
// 텍스트 파일 읽기 예제
class FileUtil {
    // fullPath 파라미터로 파일의 경로를 전달받는 메서드를 FileUtil 클래스 안에 생성
    // result 변수로 파일을 읽은 결과값을 리턴함.
    fun readTextFile(fullPath: String): String {
        // fullPath 경로를 File로 생성하고 실제 파일이 있는지 검사한다. 없으면 공백값을 리턴
        val file = File(fullPath)
        if (!file.exists()) return ""

        // FileReader로 file을 읽고 BufferedReader에 담아서 속도를 향상시킴
        val reader = FileReader(file)
        val buffer = BufferedReader(reader)

        // buffer를 통해 한 줄 씩 읽은 내용을 임시로 저장할 temp 변수를 선언하고 모든 내용을 저장할 StringBuffer를
        // result 변수로 선언

        var temp = ""
        val result = StringBuffer()

        // while 문을 반복하면서 buffer에서 한 줄을 꺼내 temp 변수에 담고 그 값이 null이라면 더이상 읽을 내용이 없음
        // 반복문을 빠져나가고 값이 있다면 (null이 아니라면) result 변수에 append() 한다.
        // 여기서 append()는 setText와 다르게 기존 내용을 유지한채 뒤에 붙여주는 메서드
        while (true) {
            temp = buffer.readLine()
            if (temp == null) break;
            else result.append(buffer)
        }
        buffer.close()
        return result.toString()

    }

    // 내부 저장소에 파일 쓰기
    // 쓰기 파일은 3개의 파라미터 사용: 파일을 사용할 디렉터리, 파일명, 작성할 내용
    // 3개의 파라미터를 가진 메서드 생성
    fun writeTextFile(directory: String, filename: String, content: String) {

        // directory가 존재하는지 검사하고 없으면 생성
        // 파일처럼 디렉터리도 File 객체에 경로를 전달하면 상태 체크 가능
        val dir = File(directory)
        if (!dir.exists()) {
            dir.mkdirs()
        }

        // 디렉터리가 생성되었으면 디렉터리에 파일명을 합해서 FileWriter로 생성
        // 생성된 FileWriter를 buffer에 담으면 쓰기 속도가 향상됩니다.
        val writer = FileWriter(directory + "/" + filename)
        val buffer = BufferedWriter(writer)

        // buffer로 내용을 쓰고 close()로 닫음
        buffer.write(content)
        buffer.close()

        

    }
}