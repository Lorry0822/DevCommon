package cn.wj.base.android.databinding.tools

internal fun Boolean?.orFalse(): Boolean {
    return this ?: false
}

internal fun Boolean?.orTrue(): Boolean {
    return this ?: true
}

internal fun String?.isNotNullAndBlank(): Boolean {
    return this != null && this.isNotBlank()
}