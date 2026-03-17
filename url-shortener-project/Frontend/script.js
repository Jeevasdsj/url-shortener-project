let urlData = [];

function shortenURL() {

    let url = document.getElementById("urlInput").value;

    fetch("http://localhost:8080/shorten", {
        method: "POST",
        body: url
    })
    .then(res => res.json())
    .then(data => {

        let newData = {
            original: url,
            short: data.shortUrl,
            created: new Date().toLocaleString(),
            clicks: 0
        };

        urlData.push(newData);

        updateTable();
        updateChart();
    });
}

function updateTable() {

    let table = document.getElementById("urlTable");
    table.innerHTML = "";

    urlData.forEach((item, index) => {

        let row = `
        <tr>
            <td>${item.original}</td>
            <td><a href="${item.short}" target="_blank">${item.short}</a></td>
            <td>${item.created}</td>
            <td>${item.clicks}</td>
            <td>
                <button class="btn copy" onclick="copyURL('${item.short}')">Copy</button>
                <button class="btn open" onclick="openURL(${index})">Open</button>
            </td>
        </tr>
        `;

        table.innerHTML += row;
    });
}

function copyURL(url) {
    navigator.clipboard.writeText(url);
    alert("Copied!");
}

function openURL(index) {
    urlData[index].clicks++;
    window.open(urlData[index].short, "_blank");
    updateTable();
    updateChart();
}

function updateChart() {

    let labels = urlData.map((_, i) => "URL " + (i + 1));
    let clicks = urlData.map(item => item.clicks);

    const ctx = document.getElementById('chart').getContext('2d');

    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: 'Clicks',
                data: clicks
            }]
        }
    });
}