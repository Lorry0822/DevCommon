@file:Suppress("unused")

package cn.wj.base.expanding

/* ***************************************************************************************** */
/*                                  Collection 拓展方法                                       */
/* ***************************************************************************************** */

/**
 * 是否为 null 或 无数据
 */
fun <T : Collection<*>> T?.isNullOrEmpty(): Boolean {
    return this == null || this.isEmpty()
}

/**
 * 是否不为 null 且 有数据
 */
fun <T : Collection<*>> T?.isNotNullAndEmpty(): Boolean {
    return !this.isNullOrEmpty()
}