function getWords() {
    request_data(draw())
}

function draw(array_data) {

    var chart = echarts.init(document.getElementById('MainContent'));

    var option = {
        tooltip: {},
        series: [{
            type: 'wordCloud',
            gridSize: 2,
            sizeRange: [12, 50],
            rotationRange: [-90, 90],
            shape: 'pentagon',
            width: 2000,
            height: 2000,
            drawOutOfBound: true,
            textStyle: {
                normal: {
                    color: function () {
                        return 'rgb(' + [
                            Math.round(Math.random() * 160),
                            Math.round(Math.random() * 160),
                            Math.round(Math.random() * 160)
                        ].join(',') + ')';
                    }
                },
                emphasis: {
                    shadowBlur: 10,
                    shadowColor: '#333'
                }
            },
            data: [

                {
                    name: 'Point Break',
                    value: 265
                }
            ]
        }]
    };

    option.series[0].data = array_data;
    console.log(option.series);

    chart.setOption(option);

    window.onresize = chart.resize;

}

function request_data(call_toDraw) {

    $.ajax({
        url: "/apt/data/word_cloud",
        type: "post",
        contentType: 'application/json; charset=utf-8', // 很重要
        success: function (daa) {

            console.log(daa.data);

            draw(daa.data);
        }
    })
}