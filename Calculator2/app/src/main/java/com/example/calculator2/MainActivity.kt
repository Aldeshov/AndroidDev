package com.example.calculator2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    enum class Action {
        NULL, PRODUCT, SUBTRACT, MULTIPLY, DIVIDE, RESULT
    }

    private var current = "0";
    var value1 : Float? = 0f;
    var isFloat = false
    var action = Action.NULL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(p0: View?) {

        if(action == Action.RESULT) {
            action = Action.NULL
            value1 = 0f
            current = ""
        }

        if(current == "0")
            current = ""

        when(p0?.id){
            button_0.id -> {
                current += "0"
            }
            button_1.id -> {
                current += "1"
            }
            button_2.id -> {
                current += "2"
            }
            button_3.id -> {
                current += "3"
            }
            button_4.id -> {
                current += "4"
            }
            button_5.id -> {
                current += "5"
            }
            button_6.id -> {
                current += "6"
            }
            button_7.id -> {
                current += "7"
            }
            button_8.id -> {
                current += "8"
            }
            button_9.id -> {
                current += "9"
            }
            button_dot.id -> {
                if (!isFloat)
                {
                    current += if(current.isNotEmpty()) {
                        "."
                    } else
                        "0."
                    isFloat = true
                }
            }
        }
        if (current == "")
            current = "0"
        Summary.text = current
    }

    fun onAction(view: View) {

        if(current.endsWith(".")) {
            current.dropLast(1)
        }

        when (action) {
            Action.NULL -> {
                value1 = current.toFloat()
                clear()

                when(view.id){
                    button_product.id -> {
                        action = Action.PRODUCT
                    }
                    button_subtract.id -> {
                        action = Action.SUBTRACT
                    }
                    button_multiply.id -> {
                        action = Action.MULTIPLY
                    }
                    button_divide.id -> {
                        action = Action.DIVIDE
                    }
                }
            }
            Action.RESULT -> {
                when(view.id){
                    button_product.id -> {
                        action = Action.PRODUCT
                    }
                    button_subtract.id -> {
                        action = Action.SUBTRACT
                    }
                    button_multiply.id -> {
                        action = Action.MULTIPLY
                    }
                    button_divide.id -> {
                        action = Action.DIVIDE
                    }
                }
            }
            else -> {
                if (current != "0") {
                    val value2 = current.toFloat()
                    clear()

                    Summary.text = getResult(value2).toString()

                    when(view.id){
                        button_product.id -> {
                            action = Action.PRODUCT
                        }
                        button_subtract.id -> {
                            action = Action.SUBTRACT
                        }
                        button_multiply.id -> {
                            action = Action.MULTIPLY
                        }
                        button_divide.id -> {
                            action = Action.DIVIDE
                        }
                    }
                }
            }
        }
    }

    fun onErase(view: View) {

        if (action == Action.RESULT) {
            action = Action.NULL
            value1 = 0f
            clear()
        }
        else {
            current = if(current.length > 1 ) {
                current.dropLast(1)
            } else
                "0"
        }

        if(!current.contains(".")) {
            isFloat = false
        }

        Summary.text = current
    }

    fun onResult(view: View) {
        if(value1 != 0f) {
            val value2 = current.toFloat()
            clear()

            Summary.text = getResult(value2).toString()
            action = Action.RESULT
        }
    }

    private fun getResult(value2: Float): Float {
        var result = 0f

        when(action) {
            Action.PRODUCT -> {
                result = value1!! + value2
                value1 = result
            }
            Action.SUBTRACT -> {
                result = value1!! - value2
                value1 = result
            }
            Action.MULTIPLY -> {
                result = value1!! * value2
                value1 = result
            }
            Action.DIVIDE -> {
                result = value1!! / value2
                value1 = result
            }
            else -> {
                Log.e("Action", action.toString())
            }
        }
        return result
    }

    private fun clear() {
        current = "0"
        isFloat = false
    }
}