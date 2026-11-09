const API_URL = 'http://localhost:8080/api/orders';
let cachedOrders = [];
let notes = localStorage.getItem('ws_notes') || '';

// Запускаємо ініціалізацію після завантаження DOM
document.addEventListener('DOMContentLoaded', () => {
    initDate();
    loadStatsFromServer();

    const notesArea = document.getElementById('notesArea');
    if (notesArea) notesArea.value = notes;

    const sPhone = document.getElementById('sPhone');
    if (sPhone) {
        sPhone.addEventListener('keydown', e => e.key === 'Enter' && searchOrder());
    }
});

function initDate() {
    const d = new Date();
    const days = ['Неділя', 'Понеділок', 'Вівторок', 'Середа', 'Четвер', 'П\'ятниця', 'Субота'];
    const months = ['Січня', 'Лютого', 'Березня', 'Квітня', 'Травня', 'Червня', 'Липня', 'Серпня', 'Вересня', 'Жовтня', 'Листопада', 'Грудня'];
    const dayNum = document.getElementById('dayNum');
    const monthName = document.getElementById('monthName');
    const weekDay = document.getElementById('weekDay');
    const topDate = document.getElementById('topDate');

    if(dayNum) dayNum.textContent = d.getDate();
    if(monthName) monthName.textContent = months[d.getMonth()] + ' ' + d.getFullYear();
    if(weekDay) weekDay.textContent = days[d.getDay()];
    if(topDate) topDate.textContent = d.getDate() + ' ' + months[d.getMonth()];
}

function switchPage(id, btn) {
    document.querySelectorAll('.page').forEach(p => p.classList.remove('active'));
    document.querySelectorAll('.nav-btn').forEach(b => b.classList.remove('active'));
    document.getElementById('page-' + id).classList.add('active');
    btn.classList.add('active');
    const titles = { home: 'Головна', orders: 'Замовлення' };
    document.getElementById('pageTitle').textContent = titles[id] || id;
    if (id === 'orders') loadOrders();
}

function loadStatsFromServer() {
    fetch(`${API_URL}/stats`)
        .then(res => {
            if (!res.ok) throw new Error('Помилка сервера');
            return res.json();
        })
        .then(stats => {
            document.getElementById('sInRepair').textContent = stats.inRepairCount;
            document.getElementById('sUrgent').textContent = stats.urgentCount;
            document.getElementById('sReady').textContent = stats.readyCount;
            document.getElementById('sOverdue').textContent = 0;
            document.getElementById('bTotal').textContent = stats.totalCount;
            document.getElementById('bIssued').textContent = stats.issuedCount;
        })
        .catch(err => {
            console.error('Помилка завантаження статистики:', err);
            document.getElementById('sInRepair').textContent = "—";
        });
}

function loadOrders() {
    fetch(API_URL)
        .then(res => res.json())
        .then(data => {
            cachedOrders = data;
            renderTable(data);
        })
        .catch(err => {
            console.error(err);
            showToast('Помилка завантаження списку замовлень', 'err');
        });
}

function addOrder() {
    const phone = document.getElementById('f-phone').value.trim();
    const client = document.getElementById('f-client').value.trim();

    if(!phone){ showToast('Введіть номер телефону', 'err'); return; }
    if(!client){ showToast('Введіть ім\'я клієнта', 'err'); return; }

    const orderData = {
        phoneNumber: phone,
        client: client,
        ordersGroup: document.getElementById('f-group').value.trim(),
        color: document.getElementById('f-color').value.trim(),
        serialNumber: document.getElementById('f-serial').value.trim(),
        registrationDate: document.getElementById('f-date').value || null,
        price: parseFloat(document.getElementById('f-price').value.trim()) || 0.0
    };

    fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(orderData)
    })
        .then(res => {
            if (res.ok) return res.text();
            throw new Error('Помилка створення');
        })
        .then(message => {
            showToast(message, 'ok');
            ['f-phone','f-client','f-group','f-color','f-serial','f-date','f-price'].forEach(i=>document.getElementById(i).value='');
            loadStatsFromServer();
        })
        .catch(err => {
            console.error(err);
            showToast('Не вдалося зберегти замовлення', 'err');
        });
}

function badge(s) {
    const m={'в ремонті':'b-active','терміново':'b-urgent','готово':'b-done','видано':'b-done'};
    return`<span class="badge ${m[s]||'b-active'}">${s||'в ремонті'}</span>`;
}

function markAsChanged(rowId) {
    const saveBtn = document.getElementById(`save-${rowId}`);
    if (saveBtn) {
        saveBtn.classList.add('changed');
    }
}

function renderTable(list) {
    const tbody = document.getElementById('ordersBody');
    if(!list.length){
        tbody.innerHTML = `<tr><td colspan="10" style="text-align:center;color:var(--dim);padding:32px">Замовлень не знайдено</td></tr>`;
        return;
    }
    tbody.innerHTML = list.map((o,i)=>`
    <tr id="row-${o.id}">
      <td style="color:var(--dim)">${i+1}</td>
      <td><input type="tel" class="cell-input" style="color:var(--accent2)" value="${o.phoneNumber||''}" oninput="markAsChanged(${o.id})" id="p-phone-${o.id}"/></td>
      <td><input type="text" class="cell-input" value="${o.client||''}" oninput="markAsChanged(${o.id})" id="p-client-${o.id}"/></td>
      <td><input type="text" class="cell-input" value="${o.ordersGroup||''}" oninput="markAsChanged(${o.id})" id="p-group-${o.id}"/></td>
      <td><input type="text" class="cell-input" value="${o.color||''}" oninput="markAsChanged(${o.id})" id="p-color-${o.id}"/></td>
      <td><input type="text" class="cell-input" style="font-size:clamp(9px,.85vw,11px);color:var(--muted)" value="${o.serialNumber||''}" oninput="markAsChanged(${o.id})" id="p-serial-${o.id}"/></td>
      <td><input type="date" class="cell-input" value="${o.registrationDate||''}" oninput="markAsChanged(${o.id})" id="p-registrationDate-${o.id}"/></td>
      <td><input type="number" step="0.01" class="cell-input" value="${o.price||0}" oninput="markAsChanged(${o.id})" id="p-price-${o.id}"/></td>
      <td>
        <select class="cell-select" onchange="markAsChanged(${o.id})" id="p-status-${o.id}">
          <option value="🔧 в ремонті" ${o.status === '🔧 в ремонті' ? 'selected' : ''}>🔧 в ремонті</option>
          <option value="🔥 терміново" ${o.status === '🔥 терміново' ? 'selected' : ''}>🔥 терміново</option>
          <option value="✅ готово" ${o.status === '✅ готово' ? 'selected' : ''}>✅ готово</option>
          <option value="📦 видано" ${o.status === '📦 видано' ? 'selected' : ''}>📦 видано</option>
        </select>
      </td>
      <td style="text-align:center;">
        <button id="save-${o.id}" onclick="updateOrder(${o.id})" class="action-btn save-btn" title="Зберегти зміни">💾</button>
        <button onclick="deleteOrder(${o.id})" class="action-btn" style="color:var(--dim)" onmouseenter="this.style.color='var(--danger)'" onmouseleave="this.style.color='var(--dim)'" title="Видалити">✕</button>
      </td>
    </tr>`).join('');
}

function updateOrder(id) {
    const phone = document.getElementById(`p-phone-${id}`).value.trim();
    const client = document.getElementById(`p-client-${id}`).value.trim();

    if(!phone || !client) {
        showToast('Телефон та Клієнт обов\'язкові!', 'err');
        return;
    }

    const updatedData = {
        id: id,
        phoneNumber: phone,
        client: client,
        ordersGroup: document.getElementById(`p-group-${id}`).value.trim(),
        color: document.getElementById(`p-color-${id}`).value.trim(),
        serialNumber: document.getElementById(`p-serial-${id}`).value.trim(),
        registrationDate: document.getElementById(`p-registrationDate-${id}`).value || null,
        price: parseFloat(document.getElementById(`p-price-${id}`).value) || 0.0,
        status: document.getElementById(`p-status-${id}`).value
    };

    fetch(`${API_URL}/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(updatedData)
    })
        .then(res => {
            if (res.ok) {
                showToast('Замовлення успішно оновлено', 'ok');
                const saveBtn = document.getElementById(`save-${id}`);
                if (saveBtn) saveBtn.classList.remove('changed');
                loadStatsFromServer();
            } else {
                throw new Error('Помилка оновлення');
            }
        })
        .catch(err => {
            console.error(err);
            showToast('Не вдалося оновити дані', 'err');
        });
}

function filterLocalTable() {
    const q = document.getElementById('filterInput').value.toLowerCase();
    const filtered = cachedOrders.filter(o =>
        (o.phoneNumber + o.client + (o.ordersGroup || '')).toLowerCase().includes(q)
    );
    renderTable(filtered);
}

function deleteOrder(id) {
    if(!confirm('Ви впевнені, що хочете видалити це замовлення?')) return;
    fetch(`${API_URL}/${id}`, { method: 'DELETE' })
        .then(res => {
            if(res.ok) {
                showToast('Видалено', 'ok');
                loadOrders();
            } else {
                showToast('Помилка видалення', 'err');
            }
        })
        .catch(err => console.error(err));
}

function searchOrder() {
    const q = document.getElementById('sPhone').value.trim();
    if(!q) return;

    fetch(`${API_URL}/search?phone=${encodeURIComponent(q)}`)
        .then(res => res.json())
        .then(found => {
            const el = document.getElementById('inlineResult');
            if(!found.length){
                el.innerHTML = `<p style="color:var(--dim);font-size:clamp(10px,1vw,12px)">Нічого не знайдено</p>`;
                return;
            }
            el.innerHTML = found.map(o=>`
            <div class="result-item">
                <div class="ri-name">${o.phoneNumber} — ${o.client||'?'}</div>
                <div class="ri-info">${[o.ordersGroup, o.color, o.serialNumber].filter(Boolean).join(' / ')||'—'}</div>
                <div style="margin-top:6px;display:flex;gap:6px;align-items:center">${badge(o.status)}<span style="color:var(--dim);font-size:clamp(9px,.85vw,11px)">${o.registrationDate?'| '+o.registrationDate:''}${o.price?' | '+o.price+' ₴':''}</span></div>
            </div>`).join('');
        })
        .catch(err => console.error(err));
}

function saveNotes() {
    notes = document.getElementById('notesArea').value;
    localStorage.setItem('ws_notes', notes);
    showToast('Нотатки збережено','ok');
}

function exportCSV() {
    if(!cachedOrders.length){showToast('Нема даних','err');return;}
    const h = 'Телефон,Клієнт,Група,Колір,Серійний,Дата,Сума,Стан';
    const rows = cachedOrders.map(o=>[o.phoneNumber,o.client,o.ordersGroup,o.color,o.serialNumber,o.registrationDate,o.price,o.status].map(v=>`"${v||''}"`).join(','));
    const blob = new Blob([h+'\n'+rows.join('\n')],{type:'text/csv;charset=utf-8;'});
    const a = document.createElement('a');a.href=URL.createObjectURL(blob);a.download='замовлення.csv';a.click();
}

let toastTimer;
function showToast(msg,type='ok') {
    const t = document.getElementById('toast');
    t.textContent = msg;t.className = 'toast '+type+' show';
    clearTimeout(toastTimer);toastTimer = setTimeout(()=>t.className='toast',2200);
}

function closeModal(){document.getElementById('searchOverlay').classList.remove('open')}