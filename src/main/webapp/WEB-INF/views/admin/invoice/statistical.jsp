<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../includes/hd.jsp"></jsp:include>
<jsp:include page="../includes/header.jsp"></jsp:include>
<jsp:include page="../includes/sidebar.jsp"></jsp:include>
<jsp:include page="../includes/container.jsp"></jsp:include>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<div class="card mb-5 mb-xl-10" id="kt_profile_details_view">
    <!--begin::Card header-->
    <div class="card-header cursor-pointer">
        <!--begin::Card title-->
        <div class="card-title m-0">
            <h3 class="fw-bold m-0">Thống kê doanh thu</h3>
        </div>
        <!--end::Card title-->
        <!--begin::Action-->
        <!--end::Action-->
    </div>
    <!--begin::Card header-->
    <!--begin::Card body-->
    <div class="card-body p-9">
        <!--begin::Wrapper-->
        <div class="row ">
            <div class="col ms-3">
                <div class="form-group mb-5 row">
                    <label for="timeEnd">Thời gian bắt đầu</label>
                    <input type="date" class="form-control dateStart" placeholder="VIP"/>
                </div>
            </div>
            <div class="col ms-3">
                <div class="form-group mb-5 row">
                    <label for="timeEnd">Thời gian kết thúc</label>
                    <input type="date" class="form-control dateEnd" id="timeEnd" name="timeEnd" placeholder="VIP"/>
                </div>

            </div>
            <div class="col ms-3">
                <div class="form-group mb-5">
                    <label for="timeEnd">Xem thông tin</label>
                    <button class="btn btn-send btn-primary form-control">Xem</button>
                </div>
            </div>
        </div>
        <hr>
        <div class="d-flex flex-stack mt-6 ">
            <table class="table table-row-dashed table-row-gray-300 gy-7" id="table-statistical">
                <!--begin::Table head-->
                <thead>
                <!--begin::Table row-->
                <tr class="text-start text-muted fw-bold fs-7 text-uppercase gs-0">
                    <th scope="col" class="min-w-100px">Sản phẩm</th>
                    <th scope="col" class="min-w-100px">Số lượng bán ra</th>
                    <th scope="col" class="min-w-100px">Doanh thu</th>
                    <th scope="col" class="min-w-100px">Lợi nhuận</th>
                </tr>
                <!--end::Table row-->
                </thead>

                <tbody class="text-gray-600 fw-semibold">

                </tbody>
                <tfoot>
                <tr class="text-start text-muted fw-bold fs-7 text-uppercase gs-0">
                    <th scope="col" class="min-w-100px  text-danger"><h3 >Tổng Doanh thu</h3></th>
                    <th><h4 class="total-money" ></h4></th>
                    <th scope="col" class="min-w-100px "><h3 >Tổng Lợi nhuận</h3></th>
                    <th><h4 class="total-profit text-danger"></h4></th>


                </tr>
            </table>
        </div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>

<script !src="">

    $(document).ready(function () {
        //get current date
        var date = new Date();
        var month = date.getMonth() + 1;
        var year = date.getFullYear();
        var endDay = new Date(year, month, 0).getDate();
        if (month < 10) {
            month = '0' + month;
        }
        if (endDay < 10) {
            endDay = '0' + endDay;
        }
        $('.dateStart').val(year + '-' + month + '-' + '01');
        $('.dateEnd').val(year + '-' + month + '-' + endDay);
    });
    $(document).on('click', '.btn-send', function () {
        //reset table
        $('#table-statistical').find('tbody').empty();
        var dateStart = $('.dateStart').val();
        var dateEnd = $('.dateEnd').val();
        let data = {
            timeStart: dateStart,
            timeEnd: dateEnd
        };
        console.log(data);
        $.ajax({
            url: '/admin/detailInvoice/statistical',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                console.log(res);
                res.data.forEach(function (item) {
                    $('#table-statistical tbody').append(getHtmlRow(item));
                });
                $('.total-money').text(validatePriceToVND(res.turnover));
                $('.total-profit').text(validatePriceToVND(res.profit));
            }
        })
    })
    let getHtmlRow = (data) => {
        return `
         <tr class="text-start">
                    <td>` + data.nameProduct + `</td>
                    <td><span class="badge badge-success">` + data.quantity + `</span></td>
                    <td>` + validatePriceToVND(data.totalMoney) + `</td>
                    <td>` + validatePriceToVND(data.profitMoney) + `</td>

         </tr>`
        console.log(data);

    }
    let validatePriceToVND = (price) => {
        return new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(price)
    }
</script>