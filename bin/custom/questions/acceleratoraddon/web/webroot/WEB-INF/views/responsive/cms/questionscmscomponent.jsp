<div class="content">
    <h1>Questions and answers</h1>
    <div>
        Max number of questions: ${numberOfQuestionsToShow}
    </div>
    <c:forEach items="${product.questions}" var="question">
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