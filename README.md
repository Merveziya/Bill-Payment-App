# BILL PAYMENT APP
This project is an Android application for customers to view their contract installations and bill amounts. The application uses various Android technologies and follows modern architectural patterns.

## Features

- View a list of contract installations.
- Display total contract accounts and total debt.
- View personal information and validate fields such as TC, phone, and email.
- View bill details and payment options.
- Sort and filter invoice items based on selected contract.
- Popups for document number and due date.

## Technologies Used

- **MVVM (Model-View-ViewModel)**: For separation of concerns and better testability.
- **Dependency Injection (DI)**: Using Hilt for managing dependencies.
- **Retrofit**: For making API calls.
- **Navigation Component**: For navigating between screens.
- **LiveData**: For observing data changes.
- **Data Binding / View Binding**: For binding UI components to data sources.
- **Design Patterns**: Applying appropriate design patterns for maintainable and scalable code.

## Screens
### List Screen 📋
 **API Data:**
- totalPriceCount: Displays the total number of contract accounts.
- totalPrice: Displays the total debt.
- list array: Populates the list of installations.

### Detail Screen 📋
**Personal Information Fields:**

- Name and surname.
- TC Identification Number (with validation).
- Email address (with validation).
- Phone number (with validation).
  
**Installation Details:**

- company: Displays the company name.
- address: Displays the address.
- installationNumber: Displays the installation number.
- contractAccountNumber: Displays the contract account number.
- amount: Displays the current debt.
  
### Invoice Details 📋

**API Data:**
- dueDate: Displays the due date.
- amount: Displays the payable amount.
- documentNumber: Displays the document number in a popup.

### Popups 📋
**Document Number Popup**
- Triggered by clicking the document icon in the invoice list.
- Displays the document number.
  
**Due Date Popup**
- Triggered by clicking the pay button in the invoice list.
- Displays the due date.

## API

- The application fetches data from the following API endpoint: 
```bash
     (https://raw.githubusercontent.com/bydevelopertr/enerjisa/main/userInvoices.json)
```


  

## HOW DOES IT LOOK
![enerjisa](https://github.com/Merveziya/BrickBreaker/assets/108355676/7084d943-0bb1-4d1e-8c15-30bbe0b3eaf8)
