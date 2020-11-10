package ge.msda.calculator7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView

    private var operand: Double = 0.0
    private var lastSecOperand: Double = 0.0
    private var operation: String = ""
    private var operationUsed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)
        clearNumber(null)

    }

    fun numberClick(view: View) {

        if (view is TextView) {

            var result: String = resultTextView.text.toString()
            val number: String = view.text.toString()

            if (result == "0") {
                result = ""

                resultTextView.text = result + number
            } else if (number == ".") {
                if (!resultTextView.text.toString().contains('.')) {
                    resultTextView.text = result + number
                }
            } else {
                resultTextView.text = result + number
            }
        }
    }

    fun operationClick(view: View) {

        if (view is TextView) {

            if (!TextUtils.isEmpty(resultTextView.text)) {
                operand = resultTextView.text.toString().toDouble()
            }

            operation = view.text.toString()

            resultTextView.text = ""
            operationUsed = true

        }

    }

    fun negateClick (view: View) {
//        Log.i("UserDebug", "Negated - " + resultTextView.text.drop(1))

        if (view is TextView) {
            if (resultTextView.text.contains('-')) {
                resultTextView.text = resultTextView.text.drop(1);
            } else {
                resultTextView.text = '-' + resultTextView.text.toString();
            }
        }
    }

    fun equalsClick(view: View) {
        val secOperandText: String = resultTextView.text.toString()

        if (!TextUtils.isEmpty(secOperandText) && operationUsed) {
            lastSecOperand = secOperandText.toDouble()
        } else {
            operand = resultTextView.text.toString().toDouble()
        }

//        Log.i("UserDebug", operation.toString())

        when (operation) {
            "+" -> resultTextView.text = (operand + lastSecOperand).toString()
            "-" -> resultTextView.text = (operand - lastSecOperand).toString()
            "*" -> resultTextView.text = (operand * lastSecOperand).toString()
            "/" -> resultTextView.text = (operand / lastSecOperand).toString()
        }

        operationUsed = false
    }

    fun clearNumber (view: View?) {
        resultTextView.text = "0"
        operation = ""
        operand = 0.0
    }

    fun deleteClick (view: View) {
        val len = resultTextView.text.length;

        if (len > 1) {
            resultTextView.text = resultTextView.text.dropLast(1)
        } else if (len == 1) {
            resultTextView.text = "0"
        }
    }

}