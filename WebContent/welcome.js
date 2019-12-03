/// Javascript to generate PDF of User Information

margins = {
    top: 150,
    bottom: 40,
    left: 30,
    width: 650
};

function expdf() {
    var pdf = new jsPDF('p', 'pt', 'a4');
    pdf.setFontSize(20);
    pdf.fromHTML( addText(pdf),
        margins.left, // x coord
        margins.top,
        {
            // y coord
            width: margins.width// max width of content on PDF
        }, function (dispose) {
            headerFooterFormatting(pdf)
        },
        margins);
    header(pdf);
    var iframe = document.createElement('iframe');
    iframe.setAttribute('style', 'position:absolute;right:100px; top:0; bottom:10px; height:100%; width:800px; padding:0;');
    // document.body.appendChild(iframe);

    var win = window.open();
    
    win.document.body.style.backgroundColor = "#444444";
    win.document.body.appendChild(iframe);

    iframe.src = pdf.output('datauristring');
};

function headerFooterFormatting(doc) {
    var totalPages = doc.internal.getNumberOfPages();

    for (var i = totalPages; i >= 1; i--) { //make this page, the current page we are currently working on.
        doc.setPage(i);
        header(doc);
    }
};

function header(doc) {
    doc.setFontSize(30);
    doc.setTextColor(40);
    doc.setFontStyle('normal');

    doc.text("User Information", margins.left + 150, 80);

    doc.line(3, 100, margins.width + 43, 100); // horizontal line at header
};

function addText(doc) {
    doc.setFontSize(20);
    doc.setFontStyle('normal');

    for (let i = 0; i < inputs.length; i++) {
        doc.text(" "+inputs[i].name.toUpperCase()+"  :   "+ inputs[i].value+" ", margins.left, margins.top+(i*40));
    }

    doc.line(3, 750, margins.width + 43, 750); // horizontal line at footer
};
