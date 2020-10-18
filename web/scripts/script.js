function XYR(){
    x = $('input[name=x]:checked').val();
    y = $('.y_in').val();
    r = $('.r_in').val();
}

function checkY(x, y, r) {
    if (!y) {
        return exceptionY('<br>Вы не ввели Y')
    } else if (isNaN(y)) {
        return exceptionY('<br>Y должен быть числом')
    } else if (y < -3 || y > 5) {
        return exceptionY('<br>Y не принадлежит [-3:5]')
    } else {
        $('.exceptionY').html('');
            return true
    }
}
function checkR(x, y, r) {
    if (!r) {
        return exceptionR('<br>Вы не ввели R')
    } else if (isNaN(r)) {
        return exceptionR('<br>R должен быть числом')
    } else if (r < 2 || r > 5) {
        return exceptionR('<br>R не принадлежит [2:5]')
    } else {
        $('.exceptionR').html('');
        return true
    }
}

function exceptionY(message) {
    $('.exceptionY').html(message);
    point(0,0, 2);
    return false
}

function exceptionR(message) {
    $('.exceptionR').html(message);
    point(0,0, 1);
    return false
}

$(function () {
    $('#send').on('click', function (event) {
        XYR();
        if(checkY(x, y, r)) {
            if (checkR(x, y, r)) {
                $.get("controller",
                    {x: x, y: y, r: r},
                    function (msg) {
                        $('.result').append(msg);
                    }
                )
            }
        }
        event.preventDefault()
    });
    $('#clear').on('click', function (event) {
        $.ajax({
            success: function () {
                $('.result_php').remove();
            }
        });
        event.preventDefault()
    });
    $('.x_in').on('input', function (){
        XYR();
        if (checkR(x,y,r))
            if (checkY(x,y,r))
                point(x, y, r);
    });
    $('.y_in').on('input', function (){
        XYR();
        if (checkY(x,y,r))
            if (checkR(x,y,r))
                point(x, y, r);
    });
    $('.r_in').on('input', function (){
        XYR();
        if (checkR(x,y,r))
            if (checkY(x,y,r))
                point(x, y, r);
    })
});

function point(x, y, r) {
    $('#point').attr("cx", (x * 120 / r + 200))
        .attr("cy", (y * -120 / r + 200));
}