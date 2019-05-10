package cn.wj.base.expanding

/* ***************************************************************************************** */
/*                                       Boolean 拓展方法                                     */
/* ***************************************************************************************** */

/**
 * 如果为 null 则为 false
 */
fun Boolean?.orFalse(): Boolean {
    return this ?: false
}

/**
 * 如果为 null 则为 true
 */
fun Boolean?.orTrue(): Boolean {
    return this ?: true
}