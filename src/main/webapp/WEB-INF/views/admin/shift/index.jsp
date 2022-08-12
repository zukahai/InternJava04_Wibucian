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
		<div id="kt_docs_fullcalendar_basic" class="py-3 px-2"></div>
	</div>
</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>
<script>
    const element = document.getElementById("kt_docs_fullcalendar_basic");

    const todayDate = moment()
        .startOf("day");
    const YM = todayDate.format("YYYY-MM");
    const YESTERDAY = todayDate.clone()
                               .subtract(1, "day")
                               .format("YYYY-MM-DD");
    const TODAY = todayDate.format("YYYY-MM-DD");
    const TOMORROW = todayDate.clone()
                              .add(1, "day")
                              .format("YYYY-MM-DD");
    const calendarEl = document.getElementById("kt_docs_fullcalendar_basic");
    const calendar = new FullCalendar.Calendar(calendarEl, {
        weekNumberCalculation: "ISO",
        allDaySlot: true,
        allDayText: "",
        headerToolbar: {
            left: "prev,next today",
            center: "title",
            right: "  ",
        },
        height: 800,
        contentHeight: 780,
        aspectRatio: 2,  // see: https://fullcalendar.io/docs/aspectRatio

        // nowIndicator: true,
        now: TODAY + "T09:25:00", // just for demo

        views: {
            timeGridWeek: {buttonText: "week"},
        },

        initialView: "timeGridWeek",
        initialDate: TODAY,
        dayMaxEvents: true, // allow "more" link when too many events
        slotLabelInterval: {
            hour: 5.5
        },

        eventSources: [
            {
                url: '${contextPath}/admin/shift',
                color: "#7a8e94",

            }
        ],

        eventDataTransform: function (eventData) {
            return {
                title: eventData.idEmployeeChange ?
                    eventData.idEmployee + " -> " + eventData.idEmployeeChange :
                    eventData.idEmployee,
                start: eventData.start,
                end: eventData.end
            }
        },

        slotLabelFormat: {
            hour: 'numeric',
            minute: '2-digit',
            omitZeroMinute: true,
            meridiem: 'short'
        },
        eventTimeFormat: {
            hour: 'numeric',
            minute: '2-digit',
            meridiem: 'short'
        },
        // slotLabelInterval: {
        //     hours: 3
        // },
        slotMinTime: "07:00:00",
        slotMaxTime: "23:00:00",

        eventDidMount(e) {
            const element = e.el
            element.style.fontSize = "14px"
            const timeTitle = element.querySelector(".fc-event-title.fc-sticky")
            timeTitle.classList.remove("fc-sticky")
            timeTitle.style.marginTop = "10px"
        },
    });

    calendar.render();
</script>

