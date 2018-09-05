package xyz.ryabov.multiplatform

private val DPIS = floatArrayOf(640f, 480f, 320f, 240f, 160f, 120f, 212.8f)

private fun convert(srcValue: Float?, srcDpi: Float, dstDpi: Float): Float? {
  if (srcValue == null) return null

  return srcValue / srcDpi * dstDpi
}

fun processNewValue(value: String?, dpiIndex: Int): Array<Float?> {
  val srcDpi = DPIS[dpiIndex]
  val srcValue = value?.toFloatOrNull()

  return DPIS.mapIndexed { i, dpi ->
    if (dpiIndex != i) {
      convert(srcValue, srcDpi, dpi)
    } else {
      srcValue
    }
  }.toTypedArray()
}
