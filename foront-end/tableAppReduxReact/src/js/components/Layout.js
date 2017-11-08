import React from "react";
import axios from "axios";
import { connect } from "react-redux"
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';

import { fetchItems, addItem, deleteItem, updateItem } from "../actions/itemsActions";

@connect((store) => {
  return {
    items: store.items.items,
    itemsFetched: store.items.fetched
  };
})
export default class Layout extends React.Component {
  constructor() {
    super();
    this.state = {
      currentPanel: '',
      toggleItemPanel: false,
      id: '',
      name: '',
      price: ''
    }

    this.fetchItems = this.fetchItems.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
    this.onChange = this.onChange.bind(this);
    this.addItem = this.addItem.bind(this);
    this.deleteItem = this.deleteItem.bind(this);
    this.updateItem = this.updateItem.bind(this);
    this.toggleItemPanel = this.toggleItemPanel.bind(this);
  }

  render() {
    const { items } = this.props;

    if (!items.length) {
      return <a class="btn btn-default btn-lg btn-block" onClick={this.fetchItems}>load items</a>
    }

    const mappedItems = items.map(item =>
      <TableElement key={item.id} id={item.id} name={item.name} price={item.price} />)

    return <div>
      <h1>RESTWCHUJ api</h1>
      <ItemsTable data={items} customInsertEditor={{ getElement: this.customNameField }} />
    </div>
  }
  componentWillMount() {

  }

  customNameField = (column, attr, editorClass, ignoreEditable) => {
    const {
        editable,
      format,
      field,
      name,
      hiddenOnInsert
      } = column;

    switch (field) {
      case "name": {
        return (
          <FormElement ref={field} name={field} value={this.state.name} onChange={this.onChange} />
        );
      }
      case "price": {
        return (
          <FormElement ref={field} name={field} value={this.state.price} onChange={this.onChange} />
        );
      }
    }

  }

  fetchItems() {
    this.props.dispatch(fetchItems())
  }
  addItem(item) {
    this.props.dispatch(addItem(item));
    this.toggleItemPanel();
  }
  deleteItem(id) {
    this.props.dispatch(addItem(item));
    this.toggleItemPanel();
  }
  updateItem(item) {
    this.props.dispatch(addItem(item));
    this.toggleItemPanel();
  }

  toggleItemPanel() {
    const statement = !this.state.toggleItemPanel;
    this.setState({ toggleItemPanel: statement });
  }

  onChange(event) {
    this.setState({ [event.target.name]: event.target.value });
  }

  onSubmit(event) {
    event.preventDefault();
    console.log(this.state);
    this.addItem({ name: this.state.name, price: this.state.price });
  }

  DeleteItemPanel() {
    <Form onSubmit={this.onSubmit} >
      <FormElement name="id" value={this.state.id} onChange={this.onChange} />
      <FormElement name="name" value={this.state.name} onChange={this.onChange} disabled />
      <FormElement name="price" value={this.state.price} onChange={this.onChange} disabled />
      <ModalButton text="delete item" />
    </Form >
  }

  UpdateItemPanel() {
    return (
      <Form onSubmit={this.onSubmit}>
        <FormElement name="id" value={this.state.id} onChange={this.onChange} />
        <FormElement name="name" value={this.state.name} onChange={this.onChange} />
        <FormElement name="price" value={this.state.price} onChange={this.onChange} />
        <ModalButton text="Update item" />
      </Form>
    );
  }

  AddItemPanel() {
    return (
      <Form onSubmit={this.onSubmit}>
        <FormElement name="name" value={this.state.name} onChange={this.onChange} />
        <FormElement name="price" value={this.state.price} onChange={this.onChange} />
        <ModalButton text="Add item" />
      </Form>
    );
  }
}

/* */
function ItemsTable(props) {
  return <BootstrapTable
    data={props.data}
    trClassName="transition-ease"
    tableStyle={{ height: '' }}
    bordered={false}

    insertRow={true}
    search
    multiColumnSearch
    filter={{ type: 'TextFilter', delay: 1 }}
    pagination
    hover
    options={options(props.data.length, props.insertModal)}>
    <TableHeaderColumn dataField='id' dataSort={true} isKey hiddenOnInsert autoValue={true} >key</TableHeaderColumn>

    <TableHeaderColumn
      dataField='name'
      dataSort={true}
      tdStyle={{ whiteSpace: 'normal' }}
      customInsertEditor={props.customInsertEditor}>Name</TableHeaderColumn>

    <TableHeaderColumn
      dataField='price'
      dataSort={true}
      dataAlign='center'
      customInsertEditor={props.customInsertEditor}>Price</TableHeaderColumn>
  </BootstrapTable>
}

function options(length, insertModal) {
  const searchField = (props) => {
    return (
      <SearchField
        className='input-lg' />
    );
  }
  return {
    sizePerPage: 5,
    paginationSize: 3,  // which size per page you want to locate as default
    clearSearch: false,
    sizePerPageList: [{
      text: '5', value: 5
    }, {
      text: '10', value: 10
    },
    {
      text: 'All', value: length
    } //you can change the dropdown list for size per page
    ],
    searchField: searchField,
    insertModal: insertModal,
  };
}

function onAfterInsertRow(row) {
  let newRowStr = '';

  for (const prop in row) {
    newRowStr += prop + ': ' + row[prop] + ' \n';
  }
  alert('The new row is:\n ' + newRowStr);

}

function Modal(props) {
  return (
    <div class="modal" style={{ display: props.display }}>
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onClick={props.onClick}>&times;</button>
            <h4 class="modal-title">{props.title}</h4>
          </div>
          <div class="modal-body">
            {props.children}
          </div>
        </div>
      </div>
    </div>
  );

}

function Form(props) {
  return (
    <div class="bs-component col-lg-10 col-lg-offset-1">
      <form class="form-horizontal " onSubmit={props.onSubmit} {...props.atrr}>
        <fieldset>
          {props.children}
        </fieldset>
      </form >
    </div>
  );
}

function FormElement(props) {
  return (
    <div class="form-group">
      <label for={props.name} class="col-lg-2 control-label">{props.name}</label>
      <div class="col-lg-10">
        <input type="text" class="form-control"
          name={props.name}
          value={props.value}
          onChange={props.onChange} disabled={props.disabled} />
      </div>
    </div>
  );
}

function ModalButton(props) {
  return (
    <div class="form-group">
      <div class="col-lg-10 col-lg-offset-2">
        <button class="btn btn-primary">
          {props.text}
        </button>
      </div>
    </div>
  );
}

function Table(props) {
  return (
    <table class="table table-striped table-hover ">
      <thead>
        <tr>
          <th>id</th>
          <th>name</th>
          <th>price</th>
        </tr>
      </thead>
      <tbody>
        {props.children}
      </tbody>
    </table>
  );
}

function TableElement(props) {
  return (
    <tr key={props.id}>
      <td>{props.id}</td>
      <td>{props.name}</td>
      <td>{props.price}</td>
    </tr>
  );
}


