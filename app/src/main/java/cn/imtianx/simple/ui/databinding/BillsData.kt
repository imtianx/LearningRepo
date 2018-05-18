package cn.imtianx.simple.ui.databinding

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/5/17 下午4:57
 */
data class BillsData(
        var date: String,
        var totalPrice: String,
        var billSingles: List<BillSingle>) {

}

data class BillSingle(
        var date: String,
        var totalPrice: String
)