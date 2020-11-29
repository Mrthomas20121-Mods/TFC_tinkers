const fs = require('fs')

function itemModel(filePath, textures={}, parent='item/generated')
{
    let model = {
        "parent": parent,
        "textures": textures
    }
    fs.writeFileSync(filePath, JSON.stringify(model, null, 2), 'utf8')
}


let metals = [
    'brass',
    'sterling_silver',
    'rose_gold'
]

let types = [
    'blank',
    'double_ingot',
    'double_sheet',
    'sheet',
    'scrap',
    'tuyere',
    'blowpipe',
    'bolt',
    'long_rod',
    'rackwheel',
    'rackwheel_piece',
    'screw',
    'sleeve',
    'wire'
]
let output = ''

for(let metal of metals)
{
    output+=`# ${metal}`
    for(let type of types)
    {
        output+=lang(['item', 'tfc_tinker', 'cast', type, metal, 'name'], [metal, type, 'cast'])
        //if(!fs.existsSync(`./src/main/resources/assets/tfc_tinker/models/item/cast/${type}`))
        //fs.mkdirSync(`./src/main/resources/assets/tfc_tinker/models/item/cast/${type}`)
        //itemModel(`./src/main/resources/assets/tfc_tinker/models/item/cast/${type}/${metal}.json`, { 'layer0': `tfc_tinker:items/cast/${metal}/${type}` })
    }
    output+='\n\n'
}

console.log(output)

function lang(parts, elements) {
    let entry = parts.join('.')
    elements.forEach((value, index, array) => {
        if(value.includes('_'))
        {
            let arr = value.split('_')
            arr.forEach((val, index, array) => array[index] = capitalizeFirstLetter(val))
            //console.log(arr)
            array[index] = arr.join(' ')
        }
        else array[index] = capitalizeFirstLetter(value)
    })
    return `\n${entry}=${elements.join(' ')}`
}

function capitalizeFirstLetter(str) {
    return str.charAt(0).toUpperCase() + str.slice(1);
}
