from flask import Flask, request, render_template, jsonify

app = Flask(__name__)

# Sample data representing the Merchandise model
merchandise_data = {
    1: {'name': 'T-Shirt', 'price': 19.99, 'description': 'A comfortable cotton t-shirt.'},
    2: {'name': 'Mug', 'price': 9.99, 'description': 'A ceramic mug.'},
    3: {'name': 'Cap', 'price': 14.99, 'description': 'A stylish baseball cap.'}
}

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/get_merchandise', methods=['POST'])
def get_merchandise():
    merchandise_id = request.form.get('merchandise_id')
    if merchandise_id:
        merchandise_id = int(merchandise_id)
        if merchandise_id in merchandise_data:
            return jsonify(merchandise_data[merchandise_id])
    return jsonify({'error': 'Merchandise not found'})

if __name__ == '__main__':
    app.run(debug=True)
