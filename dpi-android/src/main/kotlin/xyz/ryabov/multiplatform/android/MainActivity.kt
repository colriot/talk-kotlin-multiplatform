package xyz.ryabov.multiplatform.android

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import xyz.ryabov.multiplatform.MppPresenter
import xyz.ryabov.multiplatform.MppView
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(), MppView {
  private lateinit var inputs: Array<EditText>
  private lateinit var presenter: MppPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    presenter = MppPresenter()
    presenter.bind(this)

    inputs = arrayOf(
        findViewById(R.id.xxxhdpi),
        findViewById(R.id.xxhdpi),
        findViewById(R.id.xhdpi),
        findViewById(R.id.hdpi),
        findViewById(R.id.mdpi),
        findViewById(R.id.ldpi),
        findViewById(R.id.tvdpi)
    )

    inputs.forEachIndexed { i, input ->
      input.doAfterTextChanged { onUpdate(i) }
    }
  }

  override fun onDestroy() {
    presenter.unbind()
    super.onDestroy()
  }

  private fun onUpdate(index: Int) {
    val srcInput = inputs[index]

    presenter.inputProvided(srcInput.text.toString(), index)
  }

  override fun render(values: Array<Float?>) {
    values.forEachIndexed { i, value ->
      set(inputs[i], value?.format().orEmpty())
    }
  }

  private fun set(input: EditText, value: String) {
    if (input.text.toString() != value) {
      input.setText(value)
    }
  }

  private fun Float.format() = fmt.format(this)

  private val fmt = DecimalFormat().apply {
    maximumFractionDigits = 2
    decimalFormatSymbols = decimalFormatSymbols.apply {
      isGroupingUsed = false
    }
  }

  private inline fun TextView.doAfterTextChanged(crossinline action: (text: Editable?) -> Unit) {
    val textWatcher = object : TextWatcher {
      override fun afterTextChanged(s: Editable?) {
        if (this@doAfterTextChanged == currentFocus) {
          action.invoke(s)
        }
      }

      override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {}

      override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {}
    }
    addTextChangedListener(textWatcher)
  }
}
