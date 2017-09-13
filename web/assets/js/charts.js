function getWords() {
    request_data(draw())
}

function draw(array_data) {

    var chart = echarts.init(document.getElementById('word_cloud'));

    var option = {
        tooltip: {},
        series: [{
            type: 'wordCloud',
            gridSize: 8,
            sizeRange: [12, 50],
            rotationRange: [-90, 90],
            shape: 'circle',
            width: '70%',
            height: '80%',
            drawOutOfBound: false,
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


function pie_draw() {

    var chart = echarts.init(document.getElementById('pie_chart'));

    var option = {
        backgroundColor: '#2c343c',

        title: {
            text: 'Customized Pie',
            left: 'center',
            top: 20,
            textStyle: {
                color: '#ccc'
            }
        },

        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },

        visualMap: {
            show: false,
            min: 80,
            max: 600,
            inRange: {
                colorLightness: [0, 1]
            }
        },
        series : [
            {
                name:'访问来源',
                type:'pie',
                radius : '55%',
                center: ['50%', '50%'],
                data:[
                    {value:335, name:'直接访问'},
                    {value:310, name:'邮件营销'},
                    {value:274, name:'联盟广告'},
                    {value:235, name:'视频广告'},
                    {value:400, name:'搜索引擎'}
                ].sort(function (a, b) { return a.value - b.value; }),
                roseType: 'radius',
                label: {
                    normal: {
                        textStyle: {
                            color: 'rgba(255, 255, 255, 0.3)'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        lineStyle: {
                            color: 'rgba(255, 255, 255, 0.3)'
                        },
                        smooth: 0.2,
                        length: 10,
                        length2: 20
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#c23531',
                        shadowBlur: 200,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                },

                animationType: 'scale',
                animationEasing: 'elasticOut',
                animationDelay: function (idx) {
                    return Math.random() * 200;
                }
            }
        ]
    };
    // option.series[0].data = array_data;
    // console.log(option.series);

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