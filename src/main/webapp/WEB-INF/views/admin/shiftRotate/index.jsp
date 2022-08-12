<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="/">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../includes/hd.jsp"></jsp:include>
<jsp:include page="../includes/header.jsp"></jsp:include>
<jsp:include page="../includes/sidebar.jsp"></jsp:include>
<jsp:include page="../includes/container.jsp"></jsp:include>


<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="content flex-column-fluid" id="kt_content">
	<div class="card mb-5 mb-xl-10" id="kt_profile_details_view">
		<!--begin::Card header-->
		<div class="card-header flex-row">
			<div class="d-flex justify-content-center align-items-lg-start flex-column h-100px">
				<!--begin::Card title-->
				<div class="card-title m-0">
					<h3 class="fw-bold m-0">Dach sách yêu cầu xoay ca đang chờ
						được phê duyệt
					</h3>
				</div>
			</div>
		</div>
		<div class="card-body p-9">
			<!--end::Input group-->
			<div class="table-responsive">
				<table id="kt_datatable_complex_header"
					   class="table table-striped table-row-bordered gy-5 gs-7 border rounded w-100">
					<thead>
						<tr class="fw-bold fs-6 text-gray-800 px-7">
							<th rowspan="2"
								class="align-middle border-bottom border-end min-w-50px">
								STT
							</th>
							<th colspan="2" class="border-bottom border-end text-center">
								Nhân viên yêu cầu
							</th>
							<th colspan="3" class="border-bottom border-end text-center">
								Ca làm viêc yêu cầu
							</th>
							<th colspan="2" class="border-bottom border-end text-center">
								Nhân viên nhận xoay ca
							</th>
							<th colspan="3" class="border-bottom border-end text-center">
								Ca làm việc đổi
							</th>
							<th colspan="2"
								class="align-middle border-end w-300px">
							</th>
						</tr>
						<tr class="fw-bold fs-6 text-gray-800 px-7">
							<th class="min-w-125px border-end text-center">Mã nhân viên
							</th>
							<th class="min-w-125px border-end text-center">Tên nhân viên
							</th>
							<th class="min-w-125px border-end text-center">Mã ca làm</th>
							<th class="min-w-125px border-end text-center">Ngày</th>
							<th class="min-w-125px border-end text-center">Buổi</th>
							<th class="min-w-125px border-end text-center">Mã nhân viên
							</th>
							<th class="min-w-125px border-end text-center">Tên nhân viên
							</th>
							<th class="min-w-125px border-end text-center">Mã ca làm</th>
							<th class="min-w-125px border-end text-center">Ngày</th>
							<th class="min-w-125px border-end text-center">Buổi</th>
							<th class="min-w-125px"></th>
							<th class="min-w-125px"></th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<!--end::Card body-->
	</div>
</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>

<script>
    const getDDMMYYYYFormat = (input) => {
        const day = new Date(input);
        const yyyy = day.getFullYear();
        let mm = day.getMonth() + 1; // Months start at 0!
        let dd = day.getDate();

        if (dd < 10) dd = '0' + dd;
        if (mm < 10) mm = '0' + mm;

        return dd + '-' + mm + '-' + yyyy
    }
    
    const setUpButtons = () => {
        const actionButtons = document.querySelectorAll(".action-btn")

        actionButtons.forEach(button => {
            console.log(button)
            button.addEventListener("click", async (e) => {
                const shiftRotateId = button.getAttribute("shiftrotateid")
                const action = button.getAttribute("action")
                const {data, error} = await request(shiftRotateId, action)
                if (error) {
                    window.alert(error)
                    return
                }
                window.location.reload()
            })
        })

        const request = async (shiftRotateId, action) => {
            const response = await fetch(
                "${contextPath}/admin/shiftRotate/" + shiftRotateId,
                {
                    method: "PATCH",
                    body  : new URLSearchParams({
                                                    shiftRotateId,
                                                    approve: action
                                                })
                })
            const responseData = await response.json()
            // status khác 200 nghĩa là có lỗi
            if (response.status !== 200) {
                return {
                    error: "Không thể thực hiện"
                }
            }
            return {
                data: "OK"
            }
        }
    }

    const dataTable = $("#kt_datatable_complex_header")
        .DataTable({
                       ajax        : "${contextPath}/admin/shiftRotate/pending",
                       columnDefs  : [
                           {
                               orderable: false,
                               targets  : -2,
                               data     : null,
                               render   : function (data, type, row) {
                                   return `<button class="btn btn-primary action-btn d-block mx-auto"
                                   shiftRotateId="` + row.id +
                                          `" action="true">
                                          Chấp nhận
                                               </button>`
                               }
                           },
                           {
                               orderable: false,
                               targets  : -1,
                               data     : null,
                               render   : function (data, type, row) {
                                   return `<button class="btn btn-warning action-btn w-100px d-block mx-auto"
                                   shiftRotateId="` + row.id +
                                          `" action="false">
                                          Từ chối
                                               </button>`
                               }
                           },
                           {
                               orderable     : false,
                               defaultContent: "-",
                               targets       : 0,
                           },
                           {
                               data   : "employeeId",
                               render : function (data, type, row) {
                                   <%--return `<a href="${contextPath}/">` + data + `</a>`--%>
                                   return data
                               },
                               targets: 1,
                           },
                           {
                               data   : "employeeName",
                               targets: 2,
                           },
                           {
                               // render : function (data, type, row) {
                               //     return row[data]
                               // },
                               data   : "shiftId",
                               targets: 3,
                           },
                           {
                               data   : "shiftDate",
                               render : (data) => {
                                  return getDDMMYYYYFormat(data)
                               },
                               targets: 4,
                           },
                           {
                               // render : function (data, type, row) {
                               //     return row[data]
                               // },
                               data   : "shiftCode",
                               render : (data) => {
                                   switch (data) {
                                       case 1:
                                           return "Sáng";
                                       case 2:
                                           return "Chiều";
                                       case 3:
                                           return "Tối";
                                   }
                               },
                               targets: 5,
                           },
                           {
                               // render : function (data, type, row) {
                               //     return row[data]
                               // },
                               data   : "employeeChangeId",
                               targets: 6,
                           },
                           {
                               data   : "employeeChangeName",
                               targets: 7,
                           },
                           {
                               // render : function (data, type, row) {
                               //     return row[data]
                               // },
                               data   : "shiftExchangeId",
                               targets: 8,
                           },
                           {
                               data   : "shiftExchangeDate",
                               render : (data) => {
                                   return getDDMMYYYYFormat(data)
                               },
                               targets: 9,
                           },
                           {
                               data   : "shiftExchangeCode",
                               render : (data) => {
                                   switch (data) {
                                       case 1:
                                           return "Sáng";
                                       case 2:
                                           return "Chiều";
                                       case 3:
                                           return "Tối";
                                   }
                               },
                               targets: 10,
                           },
                       ],
                       order       : [[1, 'asc']],
                       drawCallback: function () {
                           setUpButtons()
                       },
                       language    : {
                           emptyTable: "Không có yêu cầu xoay ca nào cần được phê duyệt"
                       }
                   });

    dataTable.on('order.dt', function () {
        let i = 1;

        dataTable.cells(null, 0, {order: 'applied'})
                 .every(function (cell) {
                     this.data(i++);
                 });
    })
             .draw();
</script>

<style>
    th {
        padding: 0;

    }

    td {
        text-align: center;
    }
</style>

