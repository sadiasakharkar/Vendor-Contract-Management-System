# VendorFlow - Vendor Contract Management System

A modern, enterprise-grade platform for managing vendors, contracts, and renewals with real-time analytics and reporting.
---

##  Features

###  Vendor Management
- Centralized vendor database with categorization
- Track vendor status, ownership, and contact details
- Search, filter, and paginate through vendors
- Assign vendors to multiple departments

###  Contract Lifecycle
- Create and manage contracts with vendors
- Automatic expiry tracking and renewal reminders
- Priority classification (High/Medium/Low based on expiry)
- Payment frequency tracking (Monthly/Quarterly/Yearly)

###  Dashboard & Analytics
- Real-time statistics: Active vendors, total contracts, contract value
- Expiring contracts alerts (30/90 days)
- Visual charts: Vendors by category, Contracts by status, Spending by department
- Department-wise and vendor-wise spending analysis

###  Reports
- Total vendor spending
- Department-wise breakdown
- Vendor contract counts and values
- Expiring contracts list
- Vendors without active contracts
- Export to CSV

###  Security
- Role-based access control (ADMIN, MANAGER, VIEWER)
- JWT authentication
- Secure API endpoints

---

##  Tech Stack

**Frontend**
- React 18 + TypeScript
- Tailwind CSS + shadcn/ui
- Vite (Build tool)
- React Query (Data fetching)
- Recharts (Data visualization)

**Backend**
- Spring Boot 3.2 + Java 17
- Spring Security + JWT
- MySQL 8.0
- Spring Data JPA

---
## Quick Start

### Prerequisites
- Node.js 18+
- Java 17
- MySQL 8.0

### 1. Clone & Install
```bash
git clone https://github.com/YOUR_USERNAME/vendor-contract-frontend.git
cd vendor-contract-frontend
npm install
```

### 2. Configure Environment
Create `.env.local`:
```env
VITE_API_URL=http://localhost:8080/api
VITE_ENABLE_MOCK_DATA=true
```

### 3. Run Frontend
```bash
npm run dev
# Opens at http://localhost:5173
```

### 4. Run Backend (Separate repo)
```bash
cd vendor-contract-backend
mvn spring-boot:run
# API available at http://localhost:8080
```

---

##  Default Login

| Role | Email | Password |
|------|-------|----------|
| Admin | admin@vms.com | admin123 |
| Manager | manager@vms.com | manager123 |
| Viewer | viewer@vms.com | viewer123 |

---

##  Available Commands

```bash
npm run dev      # Development server
npm run build    # Production build
npm run lint     # Code linting
```

---

##  Deployment

### Vercel
1. Connect GitHub repository
2. Set environment variables
3. Deploy automatically on push

### Netlify
- Build command: `npm run build`
- Publish directory: `dist`

---

## License

MIT License - feel free to use this project for learning or production.

---

##  Contributing

1. Fork the repository
2. Create a feature branch
3. Submit a Pull Request


<div align="center">
</div>
