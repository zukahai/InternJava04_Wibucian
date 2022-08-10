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
		<c:choose>
			<c:when test="${isInShiftApproveTime eq false}">
				<div class="card-body p-9 fs-2 text-center">Xin lỗi, hiện tại không phải
					là thời gian
					chốt ca làm việc.
				</div>
			</c:when>
			<c:when test="${isAlreadyApprovedForNextWeek eq true}">
				<div class="card-body p-9 fs-2 text-center">
					Lịch làm việc đã được chốt
				</div>
			</c:when>
			<c:otherwise>
				<!--begin::Card header-->
				<div class="card-header flex-row">
					<div class="d-flex justify-content-center align-items-lg-start flex-column h-100px">
						<!--begin::Card title-->
						<div class="card-title m-0">
							<h3 class="fw-bold m-0">Dach sách đăng ký lịch làm việc</h3>
						</div>
						<div class="card-title mt-2">
							<p class="fst-italic m-0 fw-bold">Thời gian: từ ${weekStart}
								đến ${weekEnd} </p>
						</div>
					</div>
				</div>
				<div class="card-body p-9">
					<!--end::Input group-->
					<div class="table-responsive">
						<table class="table table-row-bordered table-striped gy-5">
							<thead>
								<tr class="fw-bold fs-6 text-gray-800 row row-cols-4">
									<th class="col text-center"></th>
									<th class="col text-center fw-bold">Sáng</th>
									<th class="col text-center fw-bold">Chiều</th>
									<th class="col text-center fw-bold">Tối</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="day" items="${daysOfWeek}">
									<tr class="row row-cols-4">
										<td class="col d-flex align-items-center justify-content-center fw-bold">${day}</td>
										<c:forEach var="shiftOfDay"
												   items="${shiftsOfDay}">
											<td class="col d-flex flex-column gap-5 align-items-center justify-content-center">
												<c:set var="work" value="${true}"/>
												<c:forEach var="workPlan"
														   items="${workPlansForNextWeek[day][shiftOfDay]}">
													<c:if test="${workPlan.work eq false}">
														<div class="text-danger">Nghỉ
														</div>
														<c:set var="work"
															   value="${false}"/>
													</c:if>
												</c:forEach>
												<c:if test="${work eq true}">
													<c:forEach var="shiftNo"
															   items="${[0, 1]}">
														<c:choose>
															<c:when test="${not empty shiftRequestsForNextWeek[day][shiftOfDay]}">
																<c:set var="shift"
																	   value="${shiftRequestsForNextWeek[day][shiftOfDay][shiftNo]}"/>
															</c:when>
															<c:otherwise>
																<c:set var="shift"
																	   value="${null}"/>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${not empty shift}">
																<c:set var="employeeId"
																	   value="${shift.employee.id}"/>
																<c:set var="employeeName"
																	   value="${shift.employee.name}"/>
																<c:set var="shiftId"
																	   value="${shift.id}"/>
																<c:set var="shiftDate"
																	   value="${shift.shiftDate}"/>
																<c:set var="shiftCode"
																	   value="${shift.shiftCode}"/>
															</c:when>
															<c:otherwise>
																<c:set var="employeeId"
																	   value=""/>
																<c:set var="employeeName"
																	   value=""/>
																<c:set var="shiftId"
																	   value=""/>
																<c:set var="shiftDate"
																	   value=""/>
																<c:set var="shiftCode"
																	   value=""/>
															</c:otherwise>
														</c:choose>
														<div>
															<input type="text"
																   class="form-control w-200px h-40px text-center input${day.value}${shiftOfDay.value}${shiftNo}"
																   id="input-employee"
																   value="${employeeId}"
																   shiftId="${shiftId}"
																   dayOfWeek="${day.value}"
																   shiftOfDay="${shiftOfDay.value}"
																   shiftNo="${shiftNo}"
																   data-kt-menu-trigger="click"
																   data-kt-menu-placement="bottom-start"
																   data-kt-menu-overflow="true"
																   data-kt-menu-offset="0, 0"/>
															<div class="menu menu-sub menu-sub-dropdown menu-column menu-rounded menu-gray-800 menu-state-bg-primary fw-bold w-200px mh-200px overflow-auto z-index-3"
																 data-kt-menu="true"
																 id="dropdown-employee${day.value}${shiftOfDay.value}${shiftNo}"
															>
															</div>
														</div>
													</c:forEach>
												</c:if>
											
											</td>
										</c:forEach>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<a class="btn btn-primary w-200px d-block mx-auto mt-5"
					   href="${contextPath}/admin/shift/request/approve">
						Submit
					</a>
				</div>
				<!--end::Card body-->
			</c:otherwise>
		
		</c:choose>
	</div>
	
	<!--end::Dropdown wrapper-->
</div>
<script>
    let timeoutHolder = null;

    const allEmployees = []
    <c:forEach items="${allEmployeesID}" var="item">
    allEmployees.push("${item}");
    </c:forEach>

    const weekDayMapping = {}
    <c:forEach items="${weekDayMapping}" var="item">
    weekDayMapping[${item.key}] = "${item.value}"
    </c:forEach>

    const generateDropdown = (value, input, onFocus) => {
        // lấy các giá trị ngày, ca, mã ca tương ứng với input element
        const dayOfWeek = input.getAttribute("dayofweek")
        const shiftOfDay = input.getAttribute("shiftofday")
        const shiftNo = input.getAttribute("shiftno")
        const shiftId = input.getAttribute("shiftid")

        // lấy dropdown menu tương ứng với input element và clear content
        const dropdownElement = document.querySelector(
            "#dropdown-employee" + "" + dayOfWeek + "" +
            shiftOfDay + "" + shiftNo)
        dropdownElement.innerHTML = ""

        // lấy input element trong cùng một buổi (một nhóm)
        const anotherInput = document.querySelector(
            ".input" + dayOfWeek + "" + shiftOfDay + "" + (
                         1 - shiftNo))
        const excludeValue = anotherInput.value

        const previousValue = input.getAttribute("previousValue")
        const innerHTML = previousValue ? previousValue : input.value.trim()

        // lấy các mã nhân viên phù hợp với giá trị tìm kiếm
        const matches = allEmployees.filter(
            (employeeID) => employeeID.toLowerCase()
                                      .includes(value.toLowerCase()) &&
                            employeeID !== previousValue && employeeID !== excludeValue)
        if (onFocus && matches.length === 1 || previousValue) {
            const dropdownItemContainer = document.createElement("div")
            const dropdownItem = document.createElement("div")
            dropdownItem.classList.add(...(
                "menu-link fs-6 text-dark fw-bold px-3 py-2 justify-content-center".split(
                    " ")))
            dropdownItem.innerHTML = innerHTML
            dropdownItemContainer.classList.add("menu-item", "px-3", "bg-secondary")
            dropdownItemContainer.appendChild(dropdownItem)
            dropdownElement.appendChild(dropdownItemContainer)
            if (onFocus && matches.length === 1) return
            const separator = document.createElement("div")
            separator.classList.add("separator", "mb-0", "opacity-25");
            dropdownElement.appendChild(separator)
        }

        // tạo các dropdown items tương ứng với các mã nhân viên
        matches.forEach(match => {
            const dropdownItemContainer = document.createElement("div")
            const dropdownItem = document.createElement("div")

            dropdownItem.classList.add("menu-link", "px-3", "employee-select",
                                       "justify-content-center")
            dropdownItem.innerHTML = match
            dropdownItem.addEventListener("click",
                                          () => onDropdownItemClick(dayOfWeek,
                                                                    shiftOfDay,
                                                                    shiftId,
                                                                    match,
                                                                    input))
            dropdownItemContainer.classList.add("menu-item", "px-3",
                                                "bg-secondary")
            dropdownItemContainer.appendChild(dropdownItem)
            dropdownElement.appendChild(dropdownItemContainer)
        })
    }

    const deleteShift = async (shiftID) => {
        const url = "${contextPath}/admin/shift/" + shiftID
        const response = await fetch(url, {
            method: "DELETE"
        })
        const responseData = await response.json()
        // status khác 200 nghĩa là xảy ra lỗi
        if (response.status !== 200) {
            return {
                success: false,
                error: {
                    message: "Failed"
                },
            }
        }
        return {
            success: true,
        }
    }

    const createShift = async (idEmployee, shiftDate, shiftCode) => {
        const url = "${contextPath}/admin/shift"
        const response = await fetch(url, {
            method: "POST",
            body: new URLSearchParams({
                                          idEmployee,
                                          shiftDate: weekDayMapping[shiftDate],
                                          shiftCode
                                      })
        })
        const responseData = await response.text()
        console.log(responseData)
        // status khác 200 nghĩa là xảy ra lỗi
        if (response.status !== 200) {
            return {
                success: false,
                error: {
                    message: "Failed"
                },
            }
        }
        return {
            success: true,
            data: {
                shift: JSON.parse(responseData)
            }
        }
    }

    const updateShift = async (shiftID, idEmployee) => {
        const url = "${contextPath}/admin/shift/" + shiftID
        const response = await fetch(url, {
            method: "PATCH",
            body: new URLSearchParams({idEmployee})
        })
        const responseData = await response.json()
        // status khác 200 nghĩa là xảy ra lỗi
        if (response.status !== 200) {
            return {
                success: false,
                error: {
                    message: "Failed"
                },

            }
        }
        return {
            success: true,
        }
    }
</script>
<script>
    // TODO: add handler click --> add or change shift
    const onDropdownItemClick = async (dayOfWeek, shiftOfDay, shiftId, employeeId,
        input) => {
        if (timeoutHolder) {
            clearTimeout(timeoutHolder)
        }
        // kiểm tra shiftId, nếu rỗng => tạo mới shift, ngược lại update shift
        if (shiftId.trim() === "") {
            // TODO: handle post create (get returned shiftId value and attach to input attribute)
            const {
                error,
                data
            } = await createShift(employeeId, dayOfWeek, shiftOfDay)
            if (error) {
                window.alert("Create failed")
                return
            }
            input.setAttribute("shiftid", data.shift.id)
            input.value = employeeId
            return
        }
        const {error} = await updateShift(shiftId, employeeId)
        if (error) {
            window.alert("Update failed")
            return
        }
        input.value = employeeId
        // TODO: handle post update (if success then set input value to employeeId, if error then show modal)
        // input.setAttribute("shiftId", "coccac")
    }

    // TODO: query all input elements and add input handler
    const inputElements = document.querySelectorAll("#input-employee");
    inputElements.forEach(input => {
        // tạo dropdown dựa theo giá trị input
        input.addEventListener("input", (e) => {
            generateDropdown(e.target.value.trim(), input)
        })

        // lưu giá trị input vào lúc focus
        input.addEventListener("focus", (e) => {
            input.setAttribute("previousValue", input.value.trim());
            generateDropdown(e.target.value.trim(), input, true)
        })

        // xử lý khi input mất focus
        input.addEventListener("blur", (e) => {
            timeoutHolder = setTimeout(async (e) => {
                const previousValue = input.getAttribute("previousValue")
                const currentValue = input.value.trim()
                // giá trị không đổi, return
                if (previousValue === currentValue) return

                // giá trị thay đổi, nếu giá trị sau khác rỗng và không nằm trong danh
                // nhân viên thì trở về giá trị trước
                // && !allEmployees.find(
                //         employee => employee.toLowerCase() === currentValue.toLowerCase())
                if (currentValue !== "") {
                    input.value = previousValue
                    return
                }

                // nếu giá trị sau là rỗng, nghĩa là đã xóa ca làm của nhân viên trước đó
                // lúc này cần thực hiện gọi đến API xóa ca làm với ID ca làm được lưu trong
                // input field
                // generateDropdown("", input)
                if (currentValue === "") {
                    const shiftID = input.getAttribute("shiftID")
                    const {
                        success,
                        error
                    } = await deleteShift(shiftID)
                    if (!success) {
                        window.alert("Delete fail");
                        input.value = previousValue
                        return
                    }
                    input.setAttribute("shiftid", "")
                }
            }, 100)
            // TODO: handle post shift delete (clear input, clear input's shiftId attribute, if error then show modal and revert to previous value)
        })
    })
</script>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>

<%--<div class="menu-item px-3 bg-secondary">--%>
<%--	<div--%>
<%--			class="menu-link px-3 employee-select"--%>
<%--			id="dropdown-employee${day.value}${shiftOfDay.value}${shiftNo}"--%>
<%--			shiftId="${shiftId}"--%>
<%--			dayOfWeek="${day.value}"--%>
<%--			shiftOfDay="${shiftOfDay.value}"--%>
<%--			shiftNo="${shiftNo}"--%>
<%--			employeeId="${employeeIdForSelect}"--%>
<%--	>--%>
<%--		${employeeIdForSelect}--%>
<%--	</div>--%>
<%--</div>--%>

<%--<div class="menu-item px-3">--%>
<%--	<div class="menu-content fs-6 text-dark fw-bold px-3 py-2 text-center">${employeeId}</div>--%>
<%--</div>--%>


