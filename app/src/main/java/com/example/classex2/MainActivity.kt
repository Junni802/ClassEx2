package com.example.classex2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		var find =many()
		find.funcTest1()	// AbsTest2클래스의 함수로출
		find.funcTest2()    // AbsTest1클래스의 함수호출

		var test12 = InterTest2()
		Log.d("abstract00", "${test12.value}")
		test12.funcTest1()
		test12.funcTest2()

		var inter = object : InterTest1 {
			// 인터페이스를 이용한 인스턴스가 하나만 필요할 경우 사용
			override var value = "전우치"
			override fun funcTest1() {
				funcTest2()
			}

			override fun funcTest2() {Log.d("abstract00", "${value} funcTest2() 함수 실행")	}

		}

		inter.funcTest1()
		inter.funcTest2()

		var inheri = ChildClass()
		inheri.showVariable()

	}

}   // InterTest1 인터페이스를 인스턴스화 하여 inter2에 저장

open class ParentClass {
	private val privateVal = 1		// 같은 클래스 내에서만 접근 가능
	protected val ProtectedVal = 2 	// 상속받은 자식클래스에서 접근 가능
	internal val internalVal = 3	// 같은 모듈 내에서 접근 가능
	val defaultVal = 4				// public이므로 어디서든 접근 가능
}

// ParentClass 를 상속받아 변수들을 출력하는 자식 클래스 ChildClass 작성(함수 : showVariable())

class ChildClass: ParentClass(){
	fun showVariable(){
//		Log.d("inheriTest", "${privateVal}")
		// private으로 선언된 멤버에는 자식클래스 에서도 접근할 수 없음
		Log.d("inheriTest", "${ProtectedVal}")
		Log.d("inheriTest", "${internalVal}")
		Log.d("inheriTest", "${defaultVal}")

	}
}

class many : AbsTest1() {
	override fun funcTest1() {
		Log.d("abstract00", "funcTest1() 함수 실행")
		funcTest2()
	}

	fun func() { funcTest2() }
}



// funcTest1() 이라는 obstract 함수와 funcTest2() 라는 일반 함수 를 가진 abstract 클래스 AbsTest1
// 함수는 둘 다 리턴 없고, funcTest2() 는 "funcTest2() 함수 실행") 이라는 문자열 출력

abstract class  AbsTest1 {
	abstract fun funcTest1()

	fun funcTest2(){ Log.d("abstract00", "funcTest2() 함수 실행") }
}

interface InterTest1{
	var value : String  // 코틀린 인터페이스의 변수도 상속받는 클래스에서 구현해야 함
	fun funcTest1()
	fun funcTest2()
	// 코틀린 인터페이스의 함수는 기본적으로 'public abstract' 로 선언되며 생략 가능
}
// InterTest1 인터페이스를 상속받는 InterTest2 클래스 작성
// 변수에는 'overriding test' 입력 후 각 함수 구현 각 함수명 출력 및 변수값 출력

class InterTest2 : InterTest1 {
	// 인터페이스는 클래스가 아니므로 생성자를 호출하지 않음(즉, 괄호를 입력하지 않음)
	override var value = "박준혁"
		set(value) {"홍길동"}
	override fun funcTest1() {
		Log.d("abstract00", "funcTest1() 함수 실행")
		funcTest2()
	}

	override fun funcTest2() {Log.d("abstract00", "${value} funcTest2() 함수 실행")	}
}

// object를 사용하여 InterTest1 인터페이스를 구현하여 실행

