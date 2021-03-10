<div class="content" style="font-size: ${fontSize}px">
    <h1>Questions and answers</h1>
    <div>
        Number of questions to show: ${numberOfQuestionsToShow}
    </div>
    <c:forEach items="${product.questions}" var="question" end="${numberOfQuestionsToShow}">
        <div>Question:</div>
        <div>${question.question}</div>
        <div>By ${question.questionCustomer}</div>
        <c:if test="${not empty question.answer}">
            <div> Answer:</div>
            <div>${question.answer}</div>
            <div>By ${question.answerCustomer}</div>
        </c:if>
        <br>
    </c:forEach>
</div>